package com.kynsof.patients.application.command.dependents.createByPatientId;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateDependentByPatientIdMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_DEPENDENT_PATIENT";

    public CreateDependentByPatientIdMessage(UUID id) {
        this.id = id;
    }

}
