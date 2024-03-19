package com.kynsoft.gateway.infrastructure.keycloak.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.domain.interfaces.IOtpService;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import com.kynsoft.gateway.infrastructure.keycloak.KeycloakProvider;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerRegisterUserEventService;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerTriggerPasswordResetEventService;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerUpdateUserEventService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
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
    @Autowired
    private ProducerUpdateUserEventService producerUpdateUserEventService;

    @Autowired
    private final IOtpService otpService;

    @Autowired
    private ProducerTriggerPasswordResetEventService producerOtp;

    public UserService(IOtpService services) {
        this.otpService = services;
    }

    @Autowired
    private RestTemplate restTemplate;

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
           // this.producerUpdateUserEventService.update(new RegisterDTO(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), "", null), id);
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

    public Mono<Boolean> changeUserPassword(String userId, String oldPassword, String newPassword) {
        // Primero, obtén el username a partir del userId, ya que necesitas el username para solicitar un token
        UserResource userResource = keycloakProvider.getRealmResource().users().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        String username = userRepresentation.getUsername();

     //   Mono<TokenResponse> authenticate = authenticate(new LoginDTO(username, oldPassword));
        CredentialRepresentation newCredential = new CredentialRepresentation();
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setTemporary(false);
        newCredential.setValue(newPassword);

        userResource.resetPassword(newCredential);

        return Mono.just(true); // Retorna true para indicar éxito en el cambio de contraseña
        

    }

}
