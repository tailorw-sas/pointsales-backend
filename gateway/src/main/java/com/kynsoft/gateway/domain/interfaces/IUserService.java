package com.kynsoft.gateway.domain.interfaces;

import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.TokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IUserService {


    /**
     * Refresca un token de acceso utilizando un token de actualización.
     *
     * @param refreshToken El token de actualización.
     * @return Un Mono que emite un Optional<TokenResponse> en caso de éxito.
     */
    Mono<Optional<TokenResponse>> refreshToken(String refreshToken);

    /**
     * Cambia el estado de un usuario (por ejemplo, habilitar o deshabilitar la cuenta).
     *
     * @param id     El identificador único del usuario.
     * @param status El nuevo estado del usuario.
     */
    void changeStatus(UUID id, String status);

    List<UserRepresentation> searchUserByUsername(String username);
    void updateUser(String id, RegisterDTO registerDTO);

    Mono<Boolean> changeUserPassword(String userId, String oldPassword, String newPassword);
}



//    Mono<TokenResponse> authenticate(LoginDTO loginDTO);
//
//    Mono<Optional<TokenResponse>> refreshToken(String refreshToken);
//
//    Mono<String> registerUser(@NonNull RegisterDTO registerDTO);
//
//    List<UserRepresentation> findAllUsers();
//

//
//    void updateUser(String id, RegisterDTO registerDTO);
//
//    void deleteUser(String id);
//
//    void changeStatus(UUID id, String status);
//
//    Mono<?> getKeycloakTokenUsingGoogleToken(String googleToken);
//   Boolean triggerPasswordReset(String email);
//   Boolean changePassword(PasswordChangeRequest changeRequest);

