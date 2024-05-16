package com.kynsof.treatments.application.command.medicine.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MedicineDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_MEDICINE";

    public MedicineDeleteMessage(UUID id) {
        this.id = id;
    }

}
