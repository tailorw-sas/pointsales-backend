package com.kynsof.patients.application.command.currenrMedication.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteCurrentMedicationMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_CURRENT_MEDICATION";

    public DeleteCurrentMedicationMessage(UUID id) {
        this.id = id;
    }

}
