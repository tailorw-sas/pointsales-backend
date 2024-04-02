package com.kynsoft.gateway.infrastructure.services.keycloak;

import com.kynsof.share.core.domain.exception.UserAlreadyExistsException;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsoft.gateway.domain.dto.role.RoleRequest;
import com.kynsoft.gateway.domain.interfaces.IRoleService;
import com.kynsoft.gateway.infrastructure.services.kafka.producer.ProducerRegisterRoleEventService;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.ClientErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private KeycloakProvider keycloakProvider;

    @Autowired
    private ProducerRegisterRoleEventService producerRegisterRoleEventService;

    @Override
    public void createRole(RoleRequest request) {
        try {
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

            this.producerRegisterRoleEventService.create(createdRole.getId(), request.getName(), request.getDescription());

            createdRole.getId();
        }catch (ClientErrorException ex){
            throw new UserAlreadyExistsException("Role already exists", new ErrorField("name", "Name is already in use"));
        }

    }

    @Override
    public List<RoleRepresentation> findAllRoles() {
        RealmResource realmResource = keycloakProvider.getRealmResource();

        ClientResource clientResource = realmResource.clients().findByClientId(keycloakProvider.getClient_id()).stream()
                .findFirst()
                .map(clientRepresentation -> realmResource.clients().get(clientRepresentation.getId()))
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return  clientResource.roles().list();
    }

    @Override
    public void assignRolesToUser(String userId, List<String> roleIds) {
        // Lógica para asignar roles a usuario
    }
}

