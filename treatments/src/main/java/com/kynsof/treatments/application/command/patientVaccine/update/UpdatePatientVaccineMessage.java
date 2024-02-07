package com.kynsof.treatments.application.command.patientVaccine.update;

import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdatePatientVaccineMessage implements ICommandMessage {


    private final String command = "UPDATE_VACCINE";

    public UpdatePatientVaccineMessage() {

    }

}
