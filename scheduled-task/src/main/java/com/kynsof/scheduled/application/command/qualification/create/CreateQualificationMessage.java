package com.kynsof.scheduled.application.command.qualification.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateQualificationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_QUALIFICATION";

    public CreateQualificationMessage(UUID id) {
        this.id = id;
    }

}
