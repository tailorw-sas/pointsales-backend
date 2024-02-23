package com.kynsoft.gateway.domain.interfaces;

import com.kynsoft.gateway.application.dto.LoginDTO;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.TokenResponse;
import io.micrometer.common.lang.NonNull;
import org.keycloak.representations.idm.UserRepresentation;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IUserService {
    Mono<TokenResponse> authenticate(LoginDTO loginDTO);

    Mono<TokenResponse> refreshToken(String refreshToken);
    String registerUser(@NonNull RegisterDTO registerDTO);
     List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUsername(String username);
    void updateUser(String id, RegisterDTO registerDTO);
    void deleteUser(String id);
//    int countUsers();

    Mono<?>  getKeycloakTokenUsingGoogleToken(String googleToken);
}
