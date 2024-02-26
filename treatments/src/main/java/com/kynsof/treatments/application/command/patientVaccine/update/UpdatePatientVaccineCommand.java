package com.kynsof.treatments.application.command.patientVaccine.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.VaccinationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePatientVaccineCommand implements ICommand {
    private UUID id;
    private UUID patientId;
    private UUID vaccineId;
    private VaccinationStatus status;


    public UpdatePatientVaccineCommand(UUID id, UUID patientId, UUID vaccineId, VaccinationStatus status){
        this.id = id;
        this.patientId = patientId;
        this.vaccineId = vaccineId;
        this.status = status;
    }

    public static UpdatePatientVaccineCommand fromRequest(UUID id, UpdatePatientVaccineRequest request) {
        return new UpdatePatientVaccineCommand(id, request.getPatientId(), request.getVaccineId(),
                request.getStatus());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdatePatientVaccineMessage();
    }
}
