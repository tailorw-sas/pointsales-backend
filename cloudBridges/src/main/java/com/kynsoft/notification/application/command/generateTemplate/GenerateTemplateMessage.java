package com.kynsoft.notification.application.command.generateTemplate;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GenerateTemplateMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_ADVERTISING_CONTENT";

    public GenerateTemplateMessage(UUID id) {
        this.id = id;
    }

}
