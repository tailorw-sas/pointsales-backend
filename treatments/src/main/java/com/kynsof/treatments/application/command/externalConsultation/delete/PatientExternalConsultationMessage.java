package com.kynsof.treatments.application.command.externalConsultation.delete;



import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PatientExternalConsultationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PATIENT";

    public PatientExternalConsultationMessage(UUID id) {
        this.id = id;
    }

}
