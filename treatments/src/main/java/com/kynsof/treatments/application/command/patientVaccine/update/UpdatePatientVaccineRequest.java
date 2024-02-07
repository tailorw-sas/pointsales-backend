package com.kynsof.treatments.application.command.patientVaccine.update;

import com.kynsof.treatments.domain.enumDto.VaccinationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePatientVaccineRequest {
    private UUID patientId;
    private UUID vaccineId;
    private VaccinationStatus status;
}
