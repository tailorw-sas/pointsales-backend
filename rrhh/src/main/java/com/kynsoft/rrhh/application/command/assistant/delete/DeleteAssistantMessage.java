package com.kynsoft.rrhh.application.command.assistant.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteAssistantMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_Assistant";

    public DeleteAssistantMessage(UUID id) {
        this.id = id;
    }

}
