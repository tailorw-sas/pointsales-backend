package com.kynsof.treatments.domain.dto;

import com.kynsof.treatments.domain.dto.enumDto.VaccinationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PatientVaccineDto {
    private UUID id;
    private PatientDto patient;
    private VaccineDto vaccine;
    private VaccinationStatus status;
    private Date vaccinationDate;
}

