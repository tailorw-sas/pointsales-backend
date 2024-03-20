package com.kynsoft.gateway.application.command.auth.autenticate;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.application.dto.LoginDTO;
import com.kynsoft.gateway.application.dto.TokenResponse;
import com.kynsoft.gateway.application.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateCommandHandler implements ICommandHandler<AuthenticateCommand> {
    private final AuthService authService;

    public AuthenticateCommandHandler(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void handle(AuthenticateCommand command) {
        TokenResponse tokenResponse = authService.authenticate(new LoginDTO(command.getUserName(), command.getPassword()));
        command.setTokenResponse(tokenResponse);
    }
}
