package com.kynsoft.gateway.domain.interfaces;

import com.kynsoft.gateway.domain.dto.role.RoleRequest;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    UUID createRole(RoleRequest request);
    UUID updateRole(RoleRequest request, UUID idRole);
    List<RoleRepresentation> findAllRoles();
    void assignRolesToUser(String userId, List<String> roleIds);
}
