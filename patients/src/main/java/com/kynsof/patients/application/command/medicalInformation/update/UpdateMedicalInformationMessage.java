package com.kynsof.patients.application.command.medicalInformation.update;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateMedicalInformationMessage implements ICommandMessage {


        private final String command = "UPDATE_MEDICAL_INFORMATION";

    public UpdateMedicalInformationMessage() {

    }

}
