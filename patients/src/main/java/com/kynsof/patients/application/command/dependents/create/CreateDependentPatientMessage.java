package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateDependentPatientMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_DEPENDENT_PATIENT";

    public CreateDependentPatientMessage(UUID id) {
        this.id = id;
    }

}
