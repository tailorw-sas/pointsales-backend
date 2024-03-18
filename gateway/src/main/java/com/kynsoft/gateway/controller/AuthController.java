package com.kynsoft.gateway.controller;


import com.kynsof.share.core.domain.response.ApiResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.gateway.application.command.autenticate.AuthenticateCommand;
import com.kynsoft.gateway.application.command.autenticate.AuthenticateMessage;
import com.kynsoft.gateway.application.command.forwardPassword.ForwardPasswordCommand;
import com.kynsoft.gateway.application.command.forwardPassword.ForwardPasswordMessage;
import com.kynsoft.gateway.application.command.registry.RegistryCommand;
import com.kynsoft.gateway.application.command.registry.RegistryMessage;
import com.kynsoft.gateway.application.command.sendPasswordRecoveryOtp.SendPasswordRecoveryOtpCommand;
import com.kynsoft.gateway.application.command.sendPasswordRecoveryOtp.SendPasswordRecoveryOtpMessage;
import com.kynsoft.gateway.application.dto.*;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService userService;
    private final IMediator mediator;

    @Autowired
    public AuthController(IUserService userService, IMediator mediator) {
        this.userService = userService;
        this.mediator = mediator;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginDTO loginDTO) {
        AuthenticateCommand authenticateCommand = new AuthenticateCommand(loginDTO.getUsername(), loginDTO.getPassword());
        AuthenticateMessage response = mediator.send(authenticateCommand);
        return ResponseEntity.ok(response.getTokenResponse());
    }


   // @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Boolean>> registerUser(@RequestBody RegisterDTO registerDTO) {
        RegistryCommand command = new RegistryCommand(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getFirstname(),
                registerDTO.getLastname(), registerDTO.getPassword(), registerDTO.getRoles());
        RegistryMessage registryMessage = mediator.send(command);
        return ResponseEntity.ok(ApiResponse.success(registryMessage.getResult()));
    }

    @PostMapping("/refresh-token")
    @PreAuthorize("permitAll()")
    public Mono<ResponseEntity<?>> refreshToken(@RequestBody TokenRefreshRequest request) {
        return userService.refreshToken(request.getRefreshToken())
                .flatMap(tokenResponseOptional -> tokenResponseOptional
                        .map(tokenResponse -> Mono.just(ResponseEntity.ok(tokenResponse)))
                        .orElseGet(() -> Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(null))));
    }

    //    @PreAuthorize("permitAll()")
//    @PostMapping("/exchange-google-token")
//    public Mono<?> exchangeGoogleTokenForKeycloakToken(@RequestBody GoogleTokenRequest googleTokenRequest) {
//        return userService.getKeycloakTokenUsingGoogleToken(googleTokenRequest.getGoogleToken());
//    }
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<?>> forgotPassword(@RequestParam String email) {
        SendPasswordRecoveryOtpCommand command = new SendPasswordRecoveryOtpCommand(email);
        SendPasswordRecoveryOtpMessage sendPasswordRecoveryOtpMessage = mediator.send(command);
        return ResponseEntity.ok(ApiResponse.success(sendPasswordRecoveryOtpMessage.getResult()));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<?>> changePassword(@RequestBody PasswordChangeRequest request) {
        ForwardPasswordCommand command = new ForwardPasswordCommand(request.getEmail(),request.getNewPassword(),
                request.getOtp());
        ForwardPasswordMessage response = mediator.send(command);
        return ResponseEntity.ok(ApiResponse.success(response.getResult()));
//        Boolean response = userService.forwardPassword(request);
//        if (response) {
//            return ResponseEntity.ok(ApiResponse.success(true));
//        } else {
//            ApiError apiError = ApiError.withSingleError(
//                    "Error al cambiar la contraseña",
//                    "email",
//                    "No existe un usuario con el correo " + request.getEmail()
//            );
//            return ResponseEntity.badRequest().body(ApiResponse.fail(apiError));
//        }
    }
}
