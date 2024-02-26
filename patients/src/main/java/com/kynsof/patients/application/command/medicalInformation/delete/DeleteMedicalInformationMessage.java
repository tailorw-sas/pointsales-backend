package com.kynsof.patients.application.command.medicalInformation.delete;



import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteMedicalInformationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PATIENT";

    public DeleteMedicalInformationMessage(UUID id) {
        this.id = id;
    }

}
