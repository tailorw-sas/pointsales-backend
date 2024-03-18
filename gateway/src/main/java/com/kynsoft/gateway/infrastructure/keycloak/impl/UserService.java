package com.kynsoft.gateway.infrastructure.keycloak.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kynsof.share.core.domain.kafka.entity.UserOtpKafka;
import com.kynsoft.gateway.application.dto.LoginDTO;
import com.kynsoft.gateway.application.dto.PasswordChangeRequest;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.TokenResponse;
import com.kynsoft.gateway.domain.interfaces.IOtpService;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import com.kynsoft.gateway.infrastructure.keycloak.KeycloakProvider;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerRegisterUserEventService;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerTriggerPasswordResetEventService;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerUpdateUserEventService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private KeycloakProvider keycloakProvider;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private ProducerRegisterUserEventService producerRegisterUserEvent;
    @Autowired
    private ProducerUpdateUserEventService producerUpdateUserEventService;

    @Autowired
    private final IOtpService otpService;

    @Autowired
    private ProducerTriggerPasswordResetEventService producerOtp;

    public UserService(IOtpService services) {
        this.otpService = services;
    }

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
    public Mono<Optional<TokenResponse>> refreshToken(String refreshToken) {
        WebClient webClient = webClientBuilder.baseUrl(keycloakProvider.getTokenUri()).build();
        return webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("client_id", keycloakProvider.getClient_id())
                        .with("grant_type", "refresh_token")
                        .with("refresh_token", refreshToken)
                        .with("client_secret", keycloakProvider.getClient_secret()))
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(TokenResponse.class).map(Optional::of);
                    } else {
                        // Retornar un Optional.empty() para representar el caso de fallo.
                        return Mono.just(Optional.empty());
                    }
                });
    }

    @Override
    public Mono<String> registerUser(@NonNull RegisterDTO registerDTO) {
        int status = 0;
        UsersResource usersResource = keycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(registerDTO.getFirstname());
        userRepresentation.setLastName(registerDTO.getLastname());
        userRepresentation.setEmail(registerDTO.getEmail());
        userRepresentation.setUsername(registerDTO.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
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
            // usersResource.get(id).sendVerifyEmail();

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

            this.producerRegisterUserEvent.create(registerDTO, id);
            // customerService.save(registerDTO);
            return Mono.just("User created successfully!!");

        } else if (status == 409) {
            log.error("User exist already!");
            return Mono.just("User exist already!");
        } else {
            log.error("Error creating user, please contact with the administrator.");
            return Mono.just("User exist already!");
        }
    }

    private String extractUserIdFromLocationHeader(Response response) {
        String path = response.getLocation().getPath();
        return path.substring(path.lastIndexOf("/") + 1);
    }


    public List<UserRepresentation> findAllUsers() {
        return keycloakProvider.getRealmResource()
                .users()
                .list();
    }

    @Override
    public List<UserRepresentation> searchUserByUsername(String username) {
        return keycloakProvider.getRealmResource()
                .users()
                .searchByUsername(username, true);
    }

    @Override
    public void updateUser(String id, @NonNull RegisterDTO registerDTO) {
        // Validar el ID del usuario para asegurarse de que no esté vacío
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }

        try {
            UserResource userResource = keycloakProvider.getUserResource().get(id);
            UserRepresentation user = userResource.toRepresentation();
            if (registerDTO.getUsername() != null) {
                user.setUsername(registerDTO.getUsername());
            }
            if (registerDTO.getFirstname() != null) {
                user.setFirstName(registerDTO.getFirstname());
            }
            if (registerDTO.getLastname() != null) {
                user.setLastName(registerDTO.getLastname());
            }
            if (registerDTO.getEmail() != null) {
                user.setEmail(registerDTO.getEmail());
                user.setEmailVerified(true);
            }
            user.setEnabled(true);
            if (registerDTO.getPassword() != null && !registerDTO.getPassword().trim().isEmpty()) {
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setTemporary(false);
                credential.setType(CredentialRepresentation.PASSWORD);
                credential.setValue(registerDTO.getPassword());
                user.setCredentials(Collections.singletonList(credential));
            }
            userResource.update(user);
            this.producerUpdateUserEventService.update(new RegisterDTO(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), "", null), id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user.", e);
        }
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
            if (idToken != null)
                return Mono.just(true);
        } catch (Exception e) {
            // Log and handle the exception
            return Mono.just(false);
        }
        return Mono.just(false);
    }

    @Override
    public Boolean triggerPasswordReset(String email) {
        UsersResource userResource = keycloakProvider.getRealmResource()
                .users();
        List<UserRepresentation> users = userResource
                .searchByEmail(email, true);

        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);
            String otpCode = otpService.generateOtpCode();
            otpService.saveOtpCode(email, otpCode );
            producerOtp.create(new UserOtpKafka(email, otpCode, user.getFirstName()));
            return true;
        }
        return false;
    }

    @Override
    public Boolean changePassword(PasswordChangeRequest changeRequest) {
        if(!otpService.getOtpCode(changeRequest.getEmail()).equals(changeRequest.getOtp())){
            return false;
        }

        UsersResource userResource = keycloakProvider.getRealmResource().users();
        List<UserRepresentation> users = userResource.searchByEmail(changeRequest.getEmail(), true);

        // Asegurarse de que se encontró al menos un usuario con ese correo electrónico
        if (!users.isEmpty()) {
            UserRepresentation user = users.get(0);

            // Crear una nueva representación de credencial para la contraseña
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setTemporary(false); // Puedes decidir si la contraseña es temporal o no
            credential.setValue(changeRequest.getNewPassword());

            // Obtener el ID del usuario y utilizarlo para resetear la contraseña
            String userId = user.getId();
            userResource.get(userId).resetPassword(credential);


    }
        return true;
    }
}
