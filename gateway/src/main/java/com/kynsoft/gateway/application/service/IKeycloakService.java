package com.kynsoft.gateway.application.service;

import com.kynsoft.gateway.application.dto.RegisterDTO;
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

    String registerUser(RegisterDTO registerDTO);

    void deleteUser(String id);

    void updateUser(String id, RegisterDTO userDTO);

    List<RoleRepresentation> findAllRoles();

    String createRole(RoleRequest request);

    Mono<TokenResponse>  refreshToken(String refreshToken);
}