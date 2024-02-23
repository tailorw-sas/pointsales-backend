package com.kynsoft.gateway.controller;


import com.kynsoft.gateway.application.dto.LoginDTO;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.TokenRefreshRequest;
import com.kynsoft.gateway.application.dto.TokenResponse;
import com.kynsoft.gateway.application.service.IKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IKeycloakService keycloakService;

    @PreAuthorize("permitAll()")
    @PostMapping(value = "/authenticate", produces = "application/json")
    public Mono<TokenResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        return keycloakService.authenticate(loginDTO);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public Mono<ResponseEntity<?>> registerUser(@RequestBody RegisterDTO registerDTO) throws URISyntaxException {
        String response = keycloakService.registerUser(registerDTO);
        return Mono.justOrEmpty(ResponseEntity.created(new URI("/users/register")).body(response));
    }

    @PostMapping("/refresh-token")
    @PreAuthorize("permitAll()")
    public Mono<TokenResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        return  keycloakService.refreshToken(request.getRefreshToken());
    }
}
