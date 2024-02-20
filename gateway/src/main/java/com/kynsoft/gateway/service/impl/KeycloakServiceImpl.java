package com.kynsoft.gateway.service.impl;

import com.kynsoft.gateway.dto.LoginDTO;
import com.kynsoft.gateway.dto.RegisterDTO;
import com.kynsoft.gateway.dto.TokenResponse;
import com.kynsoft.gateway.service.IKeycloakService;
import com.kynsoft.gateway.service.kafka.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KeycloakServiceImpl implements IKeycloakService {

    @Autowired
    private com.kynsoft.gateway.util.KeycloakProvider KeycloakProvider;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String tokenUri;

    @Value("${keycloack.provider.client-id}")
    private String client_id;

    @Value("${keycloack.provider.grant-type}")
    private String grant_type;

    @Value("${keycloack.provider.client-secret}")
    private String client_secret;

    @Value("${keycloack.provider.default-role}")
    private String default_role;
    private CustomerService customerService;

    public KeycloakServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Method to user login in keycloak
     * return the token
     *
     * @return String
     */
    public Mono<TokenResponse> authenticate(LoginDTO loginDTO) {
        WebClient webClient = webClientBuilder.baseUrl(tokenUri).build();
        return webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("client_id", client_id)
                        .with("grant_type", grant_type)
                        .with("username", loginDTO.getUsername())
                        .with("password", loginDTO.getPassword())
                        .with("client_secret", client_secret))
                .retrieve()
                .bodyToMono(TokenResponse.class);
    }

    /**
     * Method to list all keycloak users
     *
     * @return List<UserRepresentation>
     */
    public List<UserRepresentation> findAllUsers() {
        return KeycloakProvider.getRealmResource()
                .users()
                .list();
    }

    /**
     * Method to list all keycloak users
     *
     * @return List<UserRepresentation>
     */
    public List<RoleRepresentation> findAllRoles() {
        RealmResource realmResource = KeycloakProvider.getRealmResource();
        String clientId = realmResource.clients().findByClientId(client_id).get(0).getId();
        ClientResource clientResource = realmResource.clients().get(clientId);
        RolesResource rolesResource = clientResource.roles();
        return rolesResource.list();
    }

    @Override
    public String registerRol(String rolName) {
        RealmResource realmResource = KeycloakProvider.getRealmResource();
        RoleRepresentation role = new RoleRepresentation();
        role.setName(rolName);
        role.setClientRole(false);
        return "Role created successfully!!";
    }

    /**
     * Method to search for a user by username
     *
     * @return List<UserRepresentation>
     */
    public List<UserRepresentation> searchUserByUsername(String username) {
        return KeycloakProvider.getRealmResource()
                .users()
                .searchByUsername(username, true);
    }

    /**
     * Method that returns the number of users
     * @return int
     */
    public int countUsers() {
        return KeycloakProvider.getRealmResource()
                .users().count();
    }

    /**
     * Method to create a user in keycloak
     * @return String
     */
    public String registerUser(@NonNull RegisterDTO registerDTO) {
        int status = 0;
        UsersResource usersResource = KeycloakProvider.getUserResource();

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

            RealmResource realmResource = KeycloakProvider.getRealmResource();

            String clientId = realmResource.clients().findByClientId(client_id).get(0).getId();

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

            customerService.save(registerDTO);
            return "User created successfully!!";

        } else if (status == 409) {
            log.error("User exist already!");
            return "User exist already!";
        } else {
            log.error("Error creating user, please contact with the administrator.");
            return "Error creating user, please contact with the administrator.";
        }
    }


    /**
     * Method to update a user in keycloak
     * @return void
     */
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

        UserResource usersResource = KeycloakProvider.getUserResource().get(id);
        usersResource.update(user);
    }

    /**
     * Method to delete a user in keycloak
     *
     * @return void
     */
    public void deleteUser(String id) {
        KeycloakProvider.getUserResource()
                .get(id)
                .remove();
    }

}
