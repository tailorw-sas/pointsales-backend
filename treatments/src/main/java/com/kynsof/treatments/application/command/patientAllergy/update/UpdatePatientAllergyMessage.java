package com.kynsof.treatments.application.command.patientAllergy.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdatePatientAllergyMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "UPDATE_DIAGNOSIS";

    public UpdatePatientAllergyMessage(boolean id) {
        this.result = id;
    }

}
