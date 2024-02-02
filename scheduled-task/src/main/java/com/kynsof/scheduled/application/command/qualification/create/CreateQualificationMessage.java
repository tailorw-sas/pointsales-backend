package com.kynsof.scheduled.application.command.qualification.create;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateQualificationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_PATIENT";

    public CreateQualificationMessage(UUID id) {
        this.id = id;
    }

}
