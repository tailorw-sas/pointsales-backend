package com.kynsoft.gateway.application.command.role.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateRoleMessage implements ICommandMessage {

    private final Boolean result;
    private final String command = "CREATE_ROLE";

    public CreateRoleMessage(Boolean result) {
        this.result = result;
    }

}
