package com.kynsoft.notification.application.command.generateTemplate;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class GenerateTemplateMessage implements ICommandMessage {

    private final byte[] result;

    private final String command = "CREATE_ADVERTISING_CONTENT";

    public GenerateTemplateMessage(byte[] result ) {
        this.result = result;
    }

}
