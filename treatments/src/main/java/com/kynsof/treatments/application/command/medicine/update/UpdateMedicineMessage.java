package com.kynsof.treatments.application.command.medicine.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateMedicineMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_MEDICINE";

    public UpdateMedicineMessage(UUID id) {
        this.id = id;
    }

}
