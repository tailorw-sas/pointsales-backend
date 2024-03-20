package com.kynsof.identity.application.command.permission.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePermissionCommand implements ICommand {

    private UUID id;
    private String name;
    private String code;
    private String description;

    public CreatePermissionCommand(String name, String code, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public static CreatePermissionCommand fromRequest(CreatePermissionRequest request) {
        return new CreatePermissionCommand(request.getName(), request.getCode(), request.getDescription());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreatePermissionMessage(id);
    }
}