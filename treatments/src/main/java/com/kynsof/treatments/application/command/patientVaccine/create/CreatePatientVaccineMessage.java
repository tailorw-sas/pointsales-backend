package com.kynsof.treatments.application.command.patientVaccine.create;

import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatePatientVaccineMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_VACCINE";

    public CreatePatientVaccineMessage(UUID id) {
        this.id = id;
    }

}
