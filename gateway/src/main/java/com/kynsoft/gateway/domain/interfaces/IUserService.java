package com.kynsoft.gateway.domain.interfaces;

import com.kynsoft.gateway.application.dto.LoginDTO;
import com.kynsoft.gateway.application.dto.PasswordChangeRequest;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.TokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IUserService {

    /**
     * Autentica un usuario y devuelve un token.
     *
     * @param loginDTO Información de inicio de sesión del usuario.
     * @return Un Mono que emite un TokenResponse en caso de éxito.
     */
    Mono<TokenResponse> authenticate(LoginDTO loginDTO);

    /**
     * Refresca un token de acceso utilizando un token de actualización.
     *
     * @param refreshToken El token de actualización.
     * @return Un Mono que emite un Optional<TokenResponse> en caso de éxito.
     */
    Mono<Optional<TokenResponse>> refreshToken(String refreshToken);

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param registerDTO Información del usuario a registrar.
     * @return Un Mono que emite el resultado de la operación de registro.
     */
    Mono<String> registerUser(RegisterDTO registerDTO);

    /**
     * Inicia un proceso de restablecimiento de contraseña para un usuario basado en su correo electrónico.
     *
     * @param email El correo electrónico del usuario para el restablecimiento de la contraseña.
     * @return Un booleano que indica si el proceso se inició correctamente.
     */
    Boolean getOtpForwardPassword(String email);

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param changeRequest Información para el cambio de contraseña.
     * @return Un booleano que indica si la contraseña se cambió correctamente.
     */
    Boolean forwardPassword(PasswordChangeRequest changeRequest);

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

