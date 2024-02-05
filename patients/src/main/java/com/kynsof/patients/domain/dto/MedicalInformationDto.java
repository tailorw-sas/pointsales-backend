package com.kynsof.patients.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalInformationDto {
    private UUID id;
    private String bloodType;
    private String medicalHistory;
    private UUID patientId;
    private PatientDto patientDto;
    private List<AllergyDto> allergies;
    private List<CurrentMedicationDto> currentMedications;
    private com.kynsof.patients.domain.dto.Status Status;

}
