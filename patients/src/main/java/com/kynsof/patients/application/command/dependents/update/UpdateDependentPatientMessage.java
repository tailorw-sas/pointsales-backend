package com.kynsof.patients.application.command.dependents.update;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateDependentPatientMessage implements ICommandMessage {


    private final String command = "UPDATE_PATIENT";

    public UpdateDependentPatientMessage() {

    }

}
