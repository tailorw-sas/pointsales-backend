package com.kynsof.patients.application.query.medicalInformation.getall;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.patients.domain.dto.*;
import com.kynsof.patients.domain.dto.enumTye.Status;
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
    private Status status;

    public MedicalInformationResponse(MedicalInformationDto medicalInformationDto) {
        this.id = medicalInformationDto.getId();
        this.patientDto = medicalInformationDto.getPatientDto();
        this.medicalHistory = medicalInformationDto.getMedicalHistory();
        this.patientId = medicalInformationDto.getPatientId();
        this.allergies= medicalInformationDto.getAllergies();
        this.currentMedications = medicalInformationDto.getCurrentMedications();
        this.status = medicalInformationDto.getStatus();
    }

}