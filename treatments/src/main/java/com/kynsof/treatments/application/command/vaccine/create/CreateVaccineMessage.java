package com.kynsof.treatments.application.command.vaccine.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateVaccineMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_PATIENT";

    public CreateVaccineMessage(UUID id) {
        this.id = id;
    }

}
