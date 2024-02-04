package com.kynsof.patients.application.command.allergy.update;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateAllergyMessage implements ICommandMessage {


    private final String command = "UPDATE_ALLERGY";

    public UpdateAllergyMessage() {

    }

}
