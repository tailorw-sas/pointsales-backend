package com.kynsoft.gateway.domain.interfaces;

import com.kynsoft.gateway.application.dto.role.RoleRequest;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface IRoleService {
    String createRole(RoleRequest request);
    List<RoleRepresentation> findAllRoles();
    void assignRolesToUser(String userId, List<String> roleIds);
}
