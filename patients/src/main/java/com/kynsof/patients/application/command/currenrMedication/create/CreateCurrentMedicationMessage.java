package com.kynsof.patients.application.command.currenrMedication.create;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCurrentMedicationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_CURRENT_MEDICATION";

    public CreateCurrentMedicationMessage(UUID id) {
        this.id = id;
    }

}
