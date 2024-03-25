package com.kynsoft.gateway.infrastructure.services.keycloak;

import lombok.Getter;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KeycloakProvider {

    @Value("${keycloak.provider.server-url}")
    private String server_url;

    @Value("${keycloak.provider.realm-name}")
    private String realm_name;

    @Value("${keycloak.provider.realm-master}")
    private String realm_master;

    @Value("${keycloak.provider.admin-clic}")
    private String admin_clic;

    @Value("${keycloak.provider.user-console}")
    private String user_console;

    @Value("${keycloak.provider.password-console}")
    private String password_console;

    @Value("${keycloak.provider.client-secret}")
    private String client_secret;
    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String tokenUri;

    @Value("${keycloak.provider.client-id}")
    private String client_id;

    @Value("${keycloak.provider.grant-type}")
    private String grant_type;

    @Value("${google.client-id}")
    private String googleClientId;

    public RealmResource getRealmResource() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(server_url)
                .realm(realm_master)
                .clientId(admin_clic)
                .username(user_console)
                .password(password_console)
                .clientSecret(client_secret)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10)
                        .build())
                .build();

        return keycloak.realm(realm_name);
    }

    public UsersResource getUserResource() {
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }
}
