package com.kynsoft.gateway.application.command.user.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateUserMessage implements ICommandMessage {

    private final Boolean result;
    private final String command = "UPDATE_USER";

    public UpdateUserMessage(Boolean result) {
        this.result = result;
    }

}
