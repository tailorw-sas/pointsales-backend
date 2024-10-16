package com.kynsof.treatments.application.command.patientAllergy.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeletePatientAllergyMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_DIAGNOSIS";

    public DeletePatientAllergyMessage(UUID id) {
        this.id = id;
    }

}
