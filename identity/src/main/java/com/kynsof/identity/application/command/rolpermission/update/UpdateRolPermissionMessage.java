package com.kynsof.identity.application.command.rolpermission.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateRolPermissionMessage implements ICommandMessage {

    private final boolean id;

    private final String command = "UPDATE_ROL_PERMISSION";

    public UpdateRolPermissionMessage(boolean id) {
        this.id = id;
    }

}
