package com.kynsoft.rrhh.application.command.assistant.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateAssistantMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_DEVICE";

    public UpdateAssistantMessage(UUID id) {
        this.id = id;
    }

}
