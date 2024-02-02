package com.kynsof.scheduled.application.command.qualification.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class QualificationDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_QUALIFICATION";

    public QualificationDeleteMessage(UUID id) {
        this.id = id;
    }

}
