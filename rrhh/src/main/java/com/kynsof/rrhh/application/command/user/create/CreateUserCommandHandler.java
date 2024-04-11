package com.kynsof.rrhh.application.command.user.create;

import com.kynsof.rrhh.doman.dto.UserSystemDto;
import com.kynsof.rrhh.doman.dto.UserSystemImageDto;
import com.kynsof.rrhh.doman.interfaces.services.IUserSystemService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements ICommandHandler<CreateUserCommand> {

    private final IUserSystemService service;

    public CreateUserCommandHandler(IUserSystemService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateUserCommand command) {
        UserSystemImageDto image = null;
        if (command.getImage() != null) {
            image = new UserSystemImageDto(UUID.randomUUID(), command.getImage());
        }
        service.create(new UserSystemDto(command.getId(), command.getIdentification(), command.getName(), command.getLastName(), command.getEmail(), "ACTIVE", image));
    }
}
