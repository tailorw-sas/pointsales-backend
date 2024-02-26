package com.kynsof.patients.application.command.medicalInformation.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateMedicalInformationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_MEDICAL_INFO";

    public CreateMedicalInformationMessage(UUID id) {
        this.id = id;
    }

}
