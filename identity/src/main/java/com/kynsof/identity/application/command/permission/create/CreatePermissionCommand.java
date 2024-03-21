package com.kynsof.identity.application.command.permission.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePermissionCommand implements ICommand {

    private UUID id;
    private String code;
    private String description;
    private Set<UUID> roles;

    public CreatePermissionCommand(String code, String description, Set<UUID> roles) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.description = description;
        this.roles = Set.copyOf(roles);
    }

    public static CreatePermissionCommand fromRequest(CreatePermissionRequest request) {
        return new CreatePermissionCommand(request.getCode(), request.getDescription(), request.getRoles());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreatePermissionMessage(id);
    }
}
