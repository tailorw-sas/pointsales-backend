package com.kynsof.patients.application.command.patients.updateadmin;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdatePatientAdminMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_PATIENT";

    public UpdatePatientAdminMessage(UUID id) {
        this.id = id;
    }

}
