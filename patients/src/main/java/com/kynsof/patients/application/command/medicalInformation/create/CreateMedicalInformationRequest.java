package com.kynsof.patients.application.command.medicalInformation.create;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateMedicalInformationRequest {

    private String bloodType;
    private String medicalHistory;
    private List<CreateAllergyRequest> allergies;
    private List<CreateCurrentMedicationRequest> currentMedications;
}
