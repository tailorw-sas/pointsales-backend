package com.kynsoft.rrhh.application.command.assistant.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateAssistantMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_ASSISTANT";

    public CreateAssistantMessage(UUID id) {
        this.id = id;
    }

}
