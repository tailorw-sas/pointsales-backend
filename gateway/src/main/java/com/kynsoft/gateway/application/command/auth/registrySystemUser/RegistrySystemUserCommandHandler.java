package com.kynsoft.gateway.application.command.auth.registrySystemUser;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.domain.dto.user.UserSystemRequest;
import com.kynsoft.gateway.infrastructure.services.keycloak.AuthService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrySystemUserCommandHandler implements ICommandHandler<RegistrySystemUserCommand> {
    private final AuthService authService;

    public RegistrySystemUserCommandHandler(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void handle(RegistrySystemUserCommand command) {
        String registerUser = authService.registerUserSystem(new UserSystemRequest(
                command.getUsername(), command.getEmail(),command.getFirstname(),
                command.getLastname(),command.getPassword(),command.getUserType()
        ), true);
        command.setId(UUID.fromString(registerUser));
    }
}
