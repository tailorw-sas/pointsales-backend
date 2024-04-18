package com.kynsof.patients.application.command.patients.createadmin;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatePatientAdminMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_PATIENT";

    public CreatePatientAdminMessage(UUID id) {
        this.id = id;
    }

}
