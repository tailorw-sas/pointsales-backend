package com.kynsof.identity.application.command.role.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateRoleMessage implements ICommandMessage {


    private final String command = "UPDATE_ROLE";

    public UpdateRoleMessage() {

    }

}
