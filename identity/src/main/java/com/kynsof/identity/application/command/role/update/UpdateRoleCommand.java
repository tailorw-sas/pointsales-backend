package com.kynsof.identity.application.command.role.update;

import com.kynsof.identity.application.command.role.create.RoleRequest;
import com.kynsof.identity.domain.dto.enumType.RoleStatusEnm;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateRoleCommand implements ICommand {

    private final UUID id;
    private final String name;
    private final String description;
    private final RoleStatusEnm status;

    public UpdateRoleCommand(UUID id, String name, String description, RoleStatusEnm status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public static UpdateRoleCommand fromRequest(UUID roleId, RoleRequest request) {
        return new UpdateRoleCommand(roleId, request.getName(), request.getDescription(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateRoleMessage(id);
    }
}
