package com.kynsof.patients.application.command.allergy.delete;



import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteAllergyMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PATIENT";

    public DeleteAllergyMessage(UUID id) {
        this.id = id;
    }

}
