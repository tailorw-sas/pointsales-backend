package com.kynsoft.gateway.application.service;

import com.kynsof.share.core.domain.exception.CustomUnauthorizedException;
import com.kynsof.share.core.domain.exception.UserAlreadyExistsException;
import com.kynsof.share.core.domain.exception.UserNotFoundException;
import com.kynsof.share.core.domain.kafka.entity.UserOtpKafka;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsoft.gateway.application.dto.LoginDTO;
import com.kynsoft.gateway.application.dto.PasswordChangeRequest;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.TokenResponse;
import com.kynsoft.gateway.domain.interfaces.IOtpService;
import com.kynsoft.gateway.infrastructure.keycloak.KeycloakProvider;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerRegisterUserEventService;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerTriggerPasswordResetEventService;
import io.micrometer.common.lang.NonNull;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthService {

    private final KeycloakProvider keycloakProvider;
    private final RestTemplate restTemplate;
    private final ProducerRegisterUserEventService producerRegisterUserEvent;
    private final IOtpService otpService;
    private final ProducerTriggerPasswordResetEventService producerOtp;

    @Autowired
    public AuthService(KeycloakProvider keycloakProvider, RestTemplate restTemplate, ProducerRegisterUserEventService producerRegisterUserEvent, IOtpService otpService, ProducerTriggerPasswordResetEventService producerOtp) {
        this.keycloakProvider = keycloakProvider;
        this.restTemplate = restTemplate;
        this.producerRegisterUserEvent = producerRegisterUserEvent;
        this.otpService = otpService;
        this.producerOtp = producerOtp;
    }

    public TokenResponse authenticate(LoginDTO loginDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", keycloakProvider.getClient_id());
        map.add("grant_type", keycloakProvider.getGrant_type());
        map.add("username", loginDTO.getUsername());
        map.add("password", loginDTO.getPassword());
        map.add("client_secret", keycloakProvider.getClient_secret());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                keycloakProvider.getTokenUri(),
                HttpMethod.POST,
                entity,
                TokenResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }


    public TokenResponse refreshToken(String refreshToken) throws CustomUnauthorizedException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", keycloakProvider.getClient_id());
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        map.add("client_secret", keycloakProvider.getClient_secret());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<TokenResponse> response = restTemplate.exchange(
                    keycloakProvider.getTokenUri(),
                    HttpMethod.POST,
                    request,
                    TokenResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException ex) {

            throw new CustomUnauthorizedException("Unauthorized: Refresh token is invalid or expired.",
                    new ErrorField("token", "Refresh token not found"));

        }
    }

    public Boolean registerUser(@NonNull RegisterDTO registerDTO) {
        UsersResource usersResource = keycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(registerDTO.getFirstname());
        userRepresentation.setLastName(registerDTO.getLastname());
        userRepresentation.setEmail(registerDTO.getEmail());
        userRepresentation.setUsername(registerDTO.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        Response response = usersResource.create(userRepresentation);

        if (response.getStatus() == 201) {
            String userId = extractUserIdFromLocation(response.getLocation().getPath());

            setNewUserPassword(registerDTO.getPassword(), userId, usersResource);
            assignRolesToUser(registerDTO.getRoles(), userId);
            producerRegisterUserEvent.create(registerDTO, userId);
            return true;
        } else if (response.getStatus() == 409) {
            throw new UserAlreadyExistsException("User already exists", new ErrorField("email", "Email is already in use"));

        } else {
            return false;
        }
    }

    public Boolean sendPasswordRecoveryOtp(String email) {
        UsersResource userResource = keycloakProvider.getRealmResource().users();
        List<UserRepresentation> users = userResource
                .searchByEmail(email, true);

        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            String otpCode = otpService.generateOtpCode();
            otpService.saveOtpCode(email, otpCode);
            producerOtp.create(new UserOtpKafka(email, otpCode, user.getFirstName()));
            return true;
        }
        throw new UserNotFoundException("User not found", new ErrorField("email", "Email not found"));
    }

    public Boolean forwardPassword(PasswordChangeRequest changeRequest) {
        if (!otpService.getOtpCode(changeRequest.getEmail()).equals(changeRequest.getOtp())) {
            return false;
        }

        UsersResource userResource = keycloakProvider.getRealmResource().users();
        List<UserRepresentation> users = userResource.searchByEmail(changeRequest.getEmail(), true);
        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setTemporary(false);
            credential.setValue(changeRequest.getNewPassword());

            String userId = user.getId();
            userResource.get(userId).resetPassword(credential);
            return true;
        }
        throw new UserNotFoundException("User not found", new ErrorField("email/password", "Change Password not found"));
    }

    private String extractUserIdFromLocation(String path) {
        return path.substring(path.lastIndexOf('/') + 1);
    }

    private void setNewUserPassword(String password, String userId, UsersResource usersResource) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        usersResource.get(userId).resetPassword(credential);
    }

    private void assignRolesToUser(List<String> roles, String userId) {
        if (roles == null || roles.isEmpty()) return;

        UsersResource usersResource = keycloakProvider.getUserResource();
        RealmResource realmResource = keycloakProvider.getRealmResource();
        String clientId = realmResource.clients().findByClientId(keycloakProvider.getClient_id()).get(0).getId();
        ClientResource clientResource = realmResource.clients().get(clientId);
        List<RoleRepresentation> roleRepresentations = new ArrayList<>();

        RolesResource rolesResource = clientResource.roles();
        for (String roleName : roles) {
            RoleRepresentation role = rolesResource.get(roleName).toRepresentation();
            roleRepresentations.add(role);
        }

        if (!roleRepresentations.isEmpty()) {
            usersResource.get(userId).roles().realmLevel().add(roleRepresentations);
        }
    }

}
