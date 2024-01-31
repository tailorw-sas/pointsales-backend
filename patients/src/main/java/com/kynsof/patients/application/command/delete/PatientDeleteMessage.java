package com.kynsof.patients.application.command.delete;



import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PatientDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PATIENT";

    public PatientDeleteMessage(UUID id) {
        this.id = id;
    }

}
