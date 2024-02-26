package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateExternalConsultationMessage implements ICommandMessage {


    private final String command = "UPDATE_EXTERNAL_CONSULTATION";

    public UpdateExternalConsultationMessage() {

    }

}
