package com.kynsof.identity.application.command.rolpermission.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteRolPermissionMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_ROL_PERMISSION";

    public DeleteRolPermissionMessage(UUID id) {
        this.id = id;
    }

}
