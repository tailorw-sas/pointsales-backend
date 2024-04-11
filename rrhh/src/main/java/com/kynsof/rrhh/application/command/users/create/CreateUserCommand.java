package com.kynsof.rrhh.application.command.users.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserCommand implements ICommand {

    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String email;
    private byte[] image;

    public CreateUserCommand(String identification, String name, String lastName, String email, byte[] image) {
        this.id = UUID.randomUUID();
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }

    public static CreateUserCommand fromRequest(CreateUserRequest request) {
        return new CreateUserCommand(
                request.getIdentification(), 
                request.getName(),
                request.getLastName(),
                request.getEmail(),
                request.getImage()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateUserMessage(id);
    }
}
