package com.kynsof.patients.application.command.medicalInformation.create;

import com.kynsof.patients.domain.dto.AllergyDto;
import com.kynsof.patients.domain.dto.CurrentMedicationDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PatientDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateMedicalInformationRequest {

    private String bloodType;
    private String medicalHistory;
    private UUID patientId;
    private List<CreateAllergyRequest> allergies;
    private List<CreateCurrentMedicationRequest> currentMedications;
}
