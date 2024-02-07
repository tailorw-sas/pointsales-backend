package com.kynsof.treatments.application.command.patientVaccine.delete;



import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PatientPatientVaccineMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PATIENT";

    public PatientPatientVaccineMessage(UUID id) {
        this.id = id;
    }

}
