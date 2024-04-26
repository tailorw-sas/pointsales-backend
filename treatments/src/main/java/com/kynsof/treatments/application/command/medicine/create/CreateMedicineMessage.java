package com.kynsof.treatments.application.command.medicine.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateMedicineMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_MEDICINE";

    public CreateMedicineMessage(UUID id) {
        this.id = id;
    }

}
