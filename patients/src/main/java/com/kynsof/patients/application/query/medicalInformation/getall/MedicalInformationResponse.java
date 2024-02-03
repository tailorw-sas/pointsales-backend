package com.kynsof.patients.application.query.medicalInformation.getall;


import com.kynsof.patients.domain.bus.query.IResponse;
import com.kynsof.patients.domain.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class MedicalInformationResponse implements IResponse {
    private UUID id;
    private String bloodType;
    private String medicalHistory;
    private UUID patientId;
    private PatientDto patientDto;
    private List<AllergyDto> allergies;
    private List<CurrentMedicationDto> currentMedications;
    private EStatusPatients status;

    public MedicalInformationResponse(MedicalInformationDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.patientDto = contactInfoDto.getPatientDto();
        this.medicalHistory = contactInfoDto.getMedicalHistory();
        this.patientId = contactInfoDto.getPatientId();
        this.allergies= contactInfoDto.getAllergies();
        this.currentMedications = contactInfoDto.getCurrentMedications();
        this.status = contactInfoDto.getStatus();
    }

}