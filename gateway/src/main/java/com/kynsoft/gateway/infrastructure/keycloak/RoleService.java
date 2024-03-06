package com.kynsoft.gateway.infrastructure.keycloak;

import com.kynsoft.gateway.application.dto.role.RoleRequest;
import com.kynsoft.gateway.domain.interfaces.IRoleService;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private KeycloakProvider keycloakProvider;

    @Override
    public String createRole(RoleRequest request) {
        RealmResource realmResource = keycloakProvider.getRealmResource();

        ClientResource clientResource = realmResource.clients().findByClientId(keycloakProvider.getClient_id()).stream()
                .findFirst()
                .map(clientRepresentation -> realmResource.clients().get(clientRepresentation.getId()))
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


        RoleRepresentation role = new RoleRepresentation();
        role.setName(request.getName());
        role.setDescription(request.getDescription());

        // Agregar atributo de estado
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("status", List.of("ACTIVE"));
        role.setAttributes(attributes);

        clientResource.roles().create(role);
        RoleRepresentation createdRole = clientResource.roles().get(request.getName()).toRepresentation();
        return createdRole.getId();
    }

    @Override
    public List<RoleRepresentation> findAllRoles() {
        return keycloakProvider.getRealmResource().clients().get("client-id").roles().list();
    }

    @Override
    public void assignRolesToUser(String userId, List<String> roleIds) {
        // Lógica para asignar roles a usuario
    }
}

