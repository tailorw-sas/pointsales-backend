package com.kynsoft.gateway.application.command.registrySystemUser;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class RegistrySystemUserCommandHandler implements ICommandHandler<RegistrySystemUserCommand> {
    private final AuthService authService;

    public RegistrySystemUserCommandHandler(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void handle(RegistrySystemUserCommand command) {
        Boolean registerUser = authService.registerUser(new RegisterDTO(
                command.getUsername(), command.getEmail(),command.getFirstname(),
                command.getLastname(),command.getPassword(), command.getRoles()
        ), true);
        command.setResul(registerUser);
    }
}
