package com.kynsoft.gateway.application.command.auth.registry;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.domain.dto.user.UserRequest;
import com.kynsoft.gateway.infrastructure.services.keycloak.AuthService;
import org.springframework.stereotype.Component;

@Component
public class RegistryCommandHandler implements ICommandHandler<RegistryCommand> {
    private final AuthService authService;

    public RegistryCommandHandler(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void handle(RegistryCommand command) {
        String registerUser = authService.registerUser(new UserRequest(
                command.getUsername(), command.getEmail(),command.getFirstname(),
                command.getLastname(),command.getPassword(), command.getRoles()
        ), false);
        command.setResul(registerUser);
    }
}
