package com.kynsof.identity.application.command.role.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateRoleCommand implements ICommand {
    private UUID id;
    private String name;
    private String description;

    public CreateRoleCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static CreateRoleCommand fromRequest(CreateRoleRequest request) {
        return new CreateRoleCommand(request.getName(), request.getDescription());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreatRoleMessage(id);
    }
}
