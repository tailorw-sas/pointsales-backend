package com.kynsof.treatments.application.query.patientVaccine.getall;


import com.kynsof.treatments.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.PatientVaccineDto;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.enumDto.VaccinationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PatientVaccineResponse implements IResponse {
    private UUID id;
    private PatientDto patient;
    private VaccineDto vaccine;
    private VaccinationStatus status;
    private Date vaccinationDate;


    public PatientVaccineResponse(PatientVaccineDto dto) {
        this.id = dto.getId();
        this.patient = dto.getPatient();
        this.vaccinationDate = dto.getVaccinationDate();
        this.status = dto.getStatus();
        this.vaccine = dto.getVaccine();
    }

}