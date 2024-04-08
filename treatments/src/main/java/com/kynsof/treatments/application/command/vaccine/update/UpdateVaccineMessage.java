package com.kynsof.treatments.application.command.vaccine.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateVaccineMessage implements ICommandMessage {


    private final String command = "UPDATE_PATIENT";

    public UpdateVaccineMessage() {

    }

}
