package com.kynsof.treatments.application.command.patientAllergy.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreatePatientAllergyMessage implements ICommandMessage {

    private final String command = "CREATE_ALL_DIAGNOSIS";

    public CreatePatientAllergyMessage() {
    }

}
