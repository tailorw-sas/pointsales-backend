package com.kynsof.identity.application.command.rolpermission.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateRolPermissionMessage implements ICommandMessage {

    private final boolean id;

    private final String command = "CREATE_ROL_PERMISSION";

    public CreateRolPermissionMessage(boolean id) {
        this.id = id;
    }

}
