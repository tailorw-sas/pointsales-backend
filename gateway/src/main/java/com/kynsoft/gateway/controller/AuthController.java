package com.kynsoft.gateway.controller;


import com.kynsof.share.core.domain.exception.CustomException;
import com.kynsof.share.core.domain.response.ApiError;
import com.kynsof.share.core.domain.response.ApiResponse;
import com.kynsoft.gateway.application.dto.*;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService userService;
    @Autowired
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping(value = "/authenticate", produces = "application/json")
    public Mono<TokenResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        return userService.authenticate(loginDTO);
    }


    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public Mono<ResponseEntity<ApiResponse<String>>> registerUser(@RequestBody RegisterDTO registerDTO) {
        return userService.registerUser(registerDTO)
                .map(response -> ResponseEntity.ok(ApiResponse.success(response)))
                .onErrorResume(e -> {
                    if (e instanceof CustomException ce) {
                        return Mono.just(ResponseEntity.status(ce.getStatus()).body(ApiResponse.fail(ce.getApiError())));
                    }
                    ApiError apiError = new ApiError("An unexpected error occurred", List.of());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.fail(apiError)));
                });
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
        Boolean response = userService.getOtpForwardPassword(email);
        if (response) {
            return ResponseEntity.ok(ApiResponse.success(true));
        } else {
            ApiError apiError = ApiError.withSingleError(
                    "Error al recuperar la contraseña",
                    "email",
                    "No existe un usuario con el correo " + email
            );
            return ResponseEntity.badRequest().body(ApiResponse.fail(apiError));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<?>> changePassword(@RequestBody PasswordChangeRequest request) {
        Boolean response = userService.forwardPassword(request);
        if (response) {
            return ResponseEntity.ok(ApiResponse.success(true));
        } else {
            ApiError apiError = ApiError.withSingleError(
                    "Error al cambiar la contraseña",
                    "email",
                    "No existe un usuario con el correo " + request.getEmail()
            );
            return ResponseEntity.badRequest().body(ApiResponse.fail(apiError));
        }
    }
}
