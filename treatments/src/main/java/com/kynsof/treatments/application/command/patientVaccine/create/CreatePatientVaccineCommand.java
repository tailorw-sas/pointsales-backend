package com.kynsof.treatments.application.command.patientVaccine.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.VaccinationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePatientVaccineCommand implements ICommand {
    private UUID id;
    private UUID patientId;
    private UUID vaccineId;
    private VaccinationStatus status;


    public CreatePatientVaccineCommand(UUID patientId, UUID vaccineId, VaccinationStatus status){

        this.patientId = patientId;
        this.vaccineId = vaccineId;
        this.status = status;
    }

    public static CreatePatientVaccineCommand fromRequest(CreatePatientVaccineRequest request) {
        return new CreatePatientVaccineCommand(request.getPatientId(), request.getVaccineId(),
                request.getStatus());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreatePatientVaccineMessage(id);
    }
}
