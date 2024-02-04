package com.kynsof.patients.application.command.allergy.create;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateAllergyMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_ALLERGY";

    public CreateAllergyMessage(UUID id) {
        this.id = id;
    }

}
