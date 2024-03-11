package com.kynsof.identity.application.command.user.create;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class CreateUserSystemCommandHandler implements ICommandHandler<CreateUserSystemCommand> {

    private final IUserSystemService userSystemService;

    @Autowired
    public CreateUserSystemCommandHandler(IUserSystemService userSystemService) {
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(CreateUserSystemCommand command) {
        UserSystemDto userDto = new UserSystemDto(
                UUID.randomUUID(),
                command.getUserName(),
                command.getEmail(),
                command.getName(),
                command.getLastName(),
                command.getStatus(),
                new ArrayList<>()
        );

       UUID id = userSystemService.create(userDto);
       command.setId(id);

    }
}
