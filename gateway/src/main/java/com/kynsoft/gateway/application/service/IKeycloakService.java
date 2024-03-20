package com.kynsoft.gateway.application.service;

import com.kynsoft.gateway.application.dto.UserRequest;
import com.kynsoft.gateway.application.dto.TokenResponse;
import com.kynsoft.gateway.application.dto.role.RoleRequest;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IKeycloakService {

//    Mono<TokenResponse> authenticate(LoginDTO loginDTO);

    List<UserRepresentation> findAllUsers();

    List<UserRepresentation> searchUserByUsername(String username);

    int countUsers();

    String registerUser(UserRequest userRequest);

    void deleteUser(String id);

    void updateUser(String id, UserRequest userDTO);

    List<RoleRepresentation> findAllRoles();

    String createRole(RoleRequest request);

    Mono<TokenResponse>  refreshToken(String refreshToken);
}
