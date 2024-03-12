package com.kynsof.identity.application.command.user.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserSystemCommand implements ICommand {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private String  status;

    public CreateUserSystemCommand(String userName, String email, String name, String lastName, String status) {
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
    }

    public static CreateUserSystemCommand fromRequest(CreateUserSystemRequest request) {
        return new CreateUserSystemCommand(
                request.getUserName(),
                request.getEmail(),
                request.getName(),
                request.getLastName(),
                request.getStatus()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateUserSystemMessage(id);
    }
}
