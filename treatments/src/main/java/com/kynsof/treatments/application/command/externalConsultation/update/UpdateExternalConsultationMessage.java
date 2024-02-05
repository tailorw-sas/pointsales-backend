package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateExternalConsultationMessage implements ICommandMessage {


    private final String command = "UPDATE_PATIENT";

    public UpdateExternalConsultationMessage() {

    }

}
