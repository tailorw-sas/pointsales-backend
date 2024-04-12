package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.domain.dto.enumType.UserType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateUserSystemCommand implements ICommand {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private UserType userType;

    public UpdateUserSystemCommand(UUID id, String userName, String email, String name, String lastName, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.userType = userType;
    }

    public static UpdateUserSystemCommand fromRequest(UUID id, UpdateUserSystemRequest request) {
        return new UpdateUserSystemCommand(
                id,
                request.getUserName(),
                request.getEmail(),
                request.getName(),
                request.getLastName(),
                request.getUserType()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateUserSystemMessage();
    }
}
