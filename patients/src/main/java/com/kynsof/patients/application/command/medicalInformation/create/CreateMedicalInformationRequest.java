package com.kynsof.patients.application.command.medicalInformation.create;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateMedicalInformationRequest {

    private String bloodType;
    private String medicalHistory;
    private List<CreateAllergyRequest> allergies;
    private List<CreateCurrentMedicationRequest> currentMedications;
}
