package com.kynsof.rrhh.application.command.users.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateUserCommand implements ICommand {

    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String email;
    private byte[] image;

    public UpdateUserCommand(UUID id, String identification, String name, String lastName, String email, byte[] image) {
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }

    public static UpdateUserCommand fromRequest(UpdateUserRequest request, UUID id) {
        return new UpdateUserCommand(
                id,
                request.getIdentification(), 
                request.getName(),
                request.getLastName(),
                request.getEmail(),
                request.getImage()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateUserMessage(id);
    }
}
