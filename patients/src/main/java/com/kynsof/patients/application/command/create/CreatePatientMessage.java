package com.kynsof.patients.application.command.create;

import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatePatientMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_PATIENT";

    public CreatePatientMessage(UUID id) {
        this.id = id;
    }

}
