package com.kynsof.treatments.application.command.externalConsultation.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateExternalConsultationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_EXTERNAL_CONSULTATION";

    public CreateExternalConsultationMessage(UUID id) {
        this.id = id;
    }

}
