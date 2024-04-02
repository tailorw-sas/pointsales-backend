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
import com.kynsoft.gateway.application.query.getById.RefreshTokenQuery;
import com.kynsoft.gateway.domain.dto.PasswordChangeRequest;
import com.kynsoft.gateway.domain.dto.TokenRefreshRequest;
import com.kynsoft.gateway.domain.dto.TokenResponse;
import com.kynsoft.gateway.domain.dto.user.LoginRequest;
import com.kynsoft.gateway.domain.dto.user.UserRequest;
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
    public Mono<ResponseEntity<TokenResponse>> authenticate(@RequestBody LoginRequest loginDTO) {
        AuthenticateCommand authenticateCommand = new AuthenticateCommand(loginDTO.getUsername(), loginDTO.getPassword());
        AuthenticateMessage response = mediator.send(authenticateCommand);
        return Mono.just(ResponseEntity.ok(response.getTokenResponse()));
    }

    // @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody UserRequest userRequest) {
        RegistryCommand command = new RegistryCommand(userRequest.getUsername(), userRequest.getEmail(), userRequest.getFirstname(),
                userRequest.getLastname(), userRequest.getPassword(), userRequest.getRoles());
        RegistryMessage registryMessage = mediator.send(command);
        return ResponseEntity.ok(ApiResponse.success(registryMessage.getResult()));
    }

    @PostMapping("/register/system/user")
    public ResponseEntity<ApiResponse<String>> registerSystemUser(@RequestBody UserRequest userRequest) {
        RegistrySystemUserCommand command = new RegistrySystemUserCommand(userRequest.getUsername(), userRequest.getEmail(), userRequest.getFirstname(),
                userRequest.getLastname(), userRequest.getPassword(), userRequest.getRoles());
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
