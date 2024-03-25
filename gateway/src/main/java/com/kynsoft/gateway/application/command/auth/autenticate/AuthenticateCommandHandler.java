package com.kynsoft.gateway.application.command.auth.autenticate;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.domain.dto.user.LoginRequest;
import com.kynsoft.gateway.domain.dto.TokenResponse;
import com.kynsoft.gateway.infrastructure.services.keycloak.AuthService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateCommandHandler implements ICommandHandler<AuthenticateCommand> {
    private final AuthService authService;

    public AuthenticateCommandHandler(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void handle(AuthenticateCommand command) {
        TokenResponse tokenResponse = authService.authenticate(new LoginRequest(command.getUserName(), command.getPassword()));
        command.setTokenResponse(tokenResponse);
    }
}
