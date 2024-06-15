package com.kynsof.rrhh.application.command.users.create;

import com.kynsof.rrhh.doman.dto.UserSystemDto;
import com.kynsof.rrhh.doman.dto.UserSystemImageDto;
import com.kynsof.rrhh.doman.interfaces.services.IUserSystemService;
import com.kynsof.rrhh.doman.rules.users.UserSystemEmailValidateRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateUserCommandHandler implements ICommandHandler<CreateUserCommand> {

    private final IUserSystemService service;

    public CreateUserCommandHandler(IUserSystemService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateUserCommand command) {
        RulesChecker.checkRule(new UserSystemEmailValidateRule(command.getEmail()));
        UserSystemImageDto image = null;
        if (command.getImage() != null) {
            image = new UserSystemImageDto(UUID.randomUUID(), command.getImage());
        }
        service.create(new UserSystemDto(command.getId(), command.getIdentification(), command.getName(), command.getLastName(), command.getEmail(), "ACTIVE", image));
    }
}
