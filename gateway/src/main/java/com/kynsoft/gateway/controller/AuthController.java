package com.kynsoft.gateway.controller;

import com.kynsof.share.core.domain.response.ApiResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.gateway.application.command.auth.autenticate.AuthenticateCommand;
import com.kynsoft.gateway.application.command.auth.autenticate.AuthenticateMessage;
import com.kynsoft.gateway.application.command.auth.forwardPassword.ForwardPasswordCommand;
import com.kynsoft.gateway.application.command.auth.forwardPassword.ForwardPasswordMessage;
import com.kynsoft.gateway.application.command.auth.registry.RegistryCommand;
import com.kynsoft.gateway.application.command.auth.registry.RegistryMessage;
import com.kynsoft.gateway.application.command.auth.registrySystemUser.RegistrySystemUserCommand;
import com.kynsoft.gateway.application.command.auth.registrySystemUser.RegistrySystemUserMessage;
import com.kynsoft.gateway.application.command.auth.sendPasswordRecoveryOtp.SendPasswordRecoveryOtpCommand;
import com.kynsoft.gateway.application.command.auth.sendPasswordRecoveryOtp.SendPasswordRecoveryOtpMessage;
import com.kynsoft.gateway.application.dto.*;
import com.kynsoft.gateway.application.query.getById.RefreshTokenQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IMediator mediator;

    @Autowired
    public AuthController( IMediator mediator) {

        this.mediator = mediator;
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/authenticate")
    public Mono<ResponseEntity<TokenResponse>> authenticate(@RequestBody LoginDTO loginDTO) {
        AuthenticateCommand authenticateCommand = new AuthenticateCommand(loginDTO.getUsername(), loginDTO.getPassword());
        AuthenticateMessage response = mediator.send(authenticateCommand);
        return Mono.just(ResponseEntity.ok(response.getTokenResponse()));
    }

    // @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Boolean>> registerUser(@RequestBody RegisterDTO registerDTO) {
        RegistryCommand command = new RegistryCommand(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getFirstname(),
                registerDTO.getLastname(), registerDTO.getPassword(), registerDTO.getRoles());
        RegistryMessage registryMessage = mediator.send(command);
        return ResponseEntity.ok(ApiResponse.success(registryMessage.getResult()));
    }

    @PostMapping("/register/system/user")
    public ResponseEntity<ApiResponse<Boolean>> registerSystemUser(@RequestBody RegisterDTO registerDTO) {
        RegistrySystemUserCommand command = new RegistrySystemUserCommand(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getFirstname(),
                registerDTO.getLastname(), registerDTO.getPassword(), registerDTO.getRoles());
        RegistrySystemUserMessage registryMessage = mediator.send(command);
        return ResponseEntity.ok(ApiResponse.success(registryMessage.getResult()));
    }

    @PostMapping("/refresh-token")
    //   @PreAuthorize("permitAll()")
    public ResponseEntity<ApiResponse<TokenResponse>> refreshToken(@RequestBody TokenRefreshRequest request) {
        RefreshTokenQuery query = new RefreshTokenQuery(request.getRefreshToken());
        TokenResponse response = mediator.send(query);
        return ResponseEntity.ok(ApiResponse.success(response));

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
        ForwardPasswordCommand command = new ForwardPasswordCommand(request.getEmail(), request.getNewPassword(),
                request.getOtp());
        ForwardPasswordMessage response = mediator.send(command);
        return ResponseEntity.ok(ApiResponse.success(response.getResult()));
    }
}
