package com.kynsoft.gateway.infrastructure.keycloak.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kynsoft.gateway.application.dto.LoginDTO;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.TokenResponse;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerRegisterUserEventService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private KeycloakProvider keycloakProvider;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private ProducerRegisterUserEventService producerRegisterUserEvent;

    @Override
    public Mono<TokenResponse> authenticate(LoginDTO loginDTO) {
        WebClient webClient = webClientBuilder.baseUrl(keycloakProvider.getTokenUri()).build();
        Mono<TokenResponse> response = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("client_id", keycloakProvider.getClient_id())
                        .with("grant_type", keycloakProvider.getGrant_type())
                        .with("username", loginDTO.getUsername())
                        .with("password", loginDTO.getPassword())
                        .with("client_secret", keycloakProvider.getClient_secret()))
                .retrieve()
                .bodyToMono(TokenResponse.class);
        return response;
    }

    @Override
    public   Mono<TokenResponse>  refreshToken(String refreshToken) {
        WebClient webClient = WebClient.builder().baseUrl(keycloakProvider.getTokenUri()).build();

        Mono<TokenResponse> response = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("client_id", keycloakProvider.getClient_id())
                        .with("grant_type", "refresh_token")
                        .with("refresh_token", refreshToken)
                        .with("client_secret", keycloakProvider.getClient_secret()))
                .retrieve()
                .bodyToMono(TokenResponse.class);
        return  response;
    }

    @Override
    public String registerUser(@NonNull RegisterDTO registerDTO) {
        int status = 0;
        UsersResource usersResource = keycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(registerDTO.getFirstname());
        userRepresentation.setLastName(registerDTO.getLastname());
        userRepresentation.setEmail(registerDTO.getEmail());
        userRepresentation.setUsername(registerDTO.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);
        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();

        if (status == 201) {
            String path = response.getLocation().getPath();
            String id = path.substring(path.lastIndexOf("/") + 1);

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(registerDTO.getPassword());

            usersResource.get(id).resetPassword(credentialRepresentation);
            usersResource.get(id).sendVerifyEmail();

            RealmResource realmResource = keycloakProvider.getRealmResource();

            String clientId = realmResource.clients().findByClientId(keycloakProvider.getClient_id()).get(0).getId();

            ClientResource clientResource = realmResource.clients().get(clientId);
            RoleMappingResource roleMappingResource = usersResource.get(id).roles();
            RolesResource rolesResource = clientResource.roles();

            List<RoleRepresentation> rolesToAdd = new ArrayList<>();

            for (String roleName : registerDTO.getRoles()) {
                RoleRepresentation role = rolesResource.get(roleName).toRepresentation();
                rolesToAdd.add(role);
            }

            if (!rolesToAdd.isEmpty()) {
                roleMappingResource.clientLevel(clientId).add(rolesToAdd);
            }

            this.producerRegisterUserEvent.create(registerDTO);
           // customerService.save(registerDTO);
            return "User created successfully!!";

        } else if (status == 409) {
            log.error("User exist already!");
            return "User exist already!";
        } else {
            log.error("Error creating user, please contact with the administrator.");
            return "Error creating user, please contact with the administrator.";
        }
    }

    public List<UserRepresentation> findAllUsers() {
        return keycloakProvider.getRealmResource()
                .users()
                .list();
    }

    public List<UserRepresentation> searchUserByUsername(String username) {
        return keycloakProvider.getRealmResource()
                .users()
                .searchByUsername(username, true);
    }

    public void updateUser(String id, @NonNull RegisterDTO registerDTO) {

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(registerDTO.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(registerDTO.getUsername());
        user.setFirstName(registerDTO.getFirstname());
        user.setLastName(registerDTO.getLastname());
        user.setEmail(registerDTO.getEmail());
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setCredentials(Collections.singletonList(credentialRepresentation));

        UserResource usersResource = keycloakProvider.getUserResource().get(id);
        usersResource.update(user);
    }

    public void deleteUser(String id) {
        keycloakProvider.getUserResource()
                .get(id)
                .remove();
    }

    @Override
    public void changeStatus(UUID id, String status) {
        UserResource usersResource = keycloakProvider.getUserResource().get(id.toString());
        UserRepresentation userRepresentation = usersResource.toRepresentation();
        userRepresentation.setEnabled(false);
        usersResource.update(userRepresentation);
    }


    public Mono<?> getKeycloakTokenUsingGoogleToken(String googleToken) {
        String googleCLientId = keycloakProvider.getGoogleClientId();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleCLientId))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(googleToken);
            if( idToken != null)
                return  Mono.just(true);
        } catch ( Exception e) {
            // Log and handle the exception
            return Mono.just(false);
        }
        return Mono.just(false);
    }
}
