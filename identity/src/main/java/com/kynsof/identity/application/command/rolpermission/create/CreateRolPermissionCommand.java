package com.kynsof.identity.application.command.rolpermission.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateRolPermissionCommand implements ICommand {

    private UUID idRol;
    private List<UUID> permissions;

    public CreateRolPermissionCommand(UUID idRol, List<UUID> permissions) {
        this.idRol = idRol;
        this.permissions = List.copyOf(permissions);        
    }

    public static CreateRolPermissionCommand fromRequest(CreateRolPermissionRequest request) {
        return new CreateRolPermissionCommand(request.getIdRol(), request.getPermissions());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateRolPermissionMessage(true);
    }
}
