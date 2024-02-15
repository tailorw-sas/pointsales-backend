package com.kynsof.scheduled.application.command.qualification.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateQualificationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_PATIENT";

    public UpdateQualificationMessage(UUID id) {
        this.id = id;
    }

}
