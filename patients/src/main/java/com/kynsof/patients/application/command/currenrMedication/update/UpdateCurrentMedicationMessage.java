package com.kynsof.patients.application.command.currenrMedication.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateCurrentMedicationMessage implements ICommandMessage {


    private final String command = "UPDATE_CURRENT_MEDICATION";

    public UpdateCurrentMedicationMessage() {

    }

}
