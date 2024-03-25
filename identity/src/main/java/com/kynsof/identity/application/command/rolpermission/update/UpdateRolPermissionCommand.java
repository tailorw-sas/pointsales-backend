package com.kynsof.identity.application.command.rolpermission.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateRolPermissionCommand implements ICommand {

    private UUID idRol;
    private List<UUID> permissions;

    public UpdateRolPermissionCommand(UUID idRol, List<UUID> permissions) {
        this.idRol = idRol;
        this.permissions = List.copyOf(permissions);        
    }

    public static UpdateRolPermissionCommand fromRequest(UpdateRolPermissionRequest request) {
        return new UpdateRolPermissionCommand(request.getIdRol(), request.getPermissions());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateRolPermissionMessage(true);
    }
}
