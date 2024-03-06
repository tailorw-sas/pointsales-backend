package com.kynsoft.gateway.controller;


import com.kynsoft.gateway.application.dto.*;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

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
    public Mono<ResponseEntity<?>> registerUser(@RequestBody RegisterDTO registerDTO) throws URISyntaxException {
        String response = userService.registerUser(registerDTO);
        return Mono.justOrEmpty(ResponseEntity.created(new URI("/users/register")).body(response));
    }

    @PostMapping("/refresh-token")
    @PreAuthorize("permitAll()")
    public Mono<ResponseEntity<?>> refreshToken(@RequestBody TokenRefreshRequest request) {
        return userService.refreshToken(request.getRefreshToken())
                .flatMap(tokenResponseOptional -> tokenResponseOptional
                        .map(tokenResponse -> Mono.just(ResponseEntity.ok(tokenResponse)))
                        .orElseGet(() -> Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).<TokenResponse>body(null))));
    }




    @PreAuthorize("permitAll()")
    @PostMapping("/exchange-google-token")
    public Mono<?> exchangeGoogleTokenForKeycloakToken(@RequestBody GoogleTokenRequest googleTokenRequest) {
        return userService.getKeycloakTokenUsingGoogleToken(googleTokenRequest.getGoogleToken());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestParam String email) {
       Boolean response = userService.triggerPasswordReset(email);
        return ResponseEntity.ok(response);
    }
}
