package com.kynsof.patients.application.command.patients.update;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdatePatientMessage implements ICommandMessage {


    private final String command = "UPDATE_PATIENT";

    public UpdatePatientMessage() {

    }

}
