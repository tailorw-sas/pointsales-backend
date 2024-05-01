package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateTreatmentMessage implements ICommandMessage {

    private final boolean id;

    private final String command = "UPDATE_TREATMENT";

    public UpdateTreatmentMessage(boolean id) {
        this.id = id;
    }

}
