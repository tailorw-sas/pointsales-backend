package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateAllTreatmentMessage implements ICommandMessage {

    private final String command = "CREATE_ALL_TREATMENT";

    public CreateAllTreatmentMessage() {
    }

}
