package com.kynsof.treatments.application.command.patientAllergy.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePatientAllergyRequest {
    private UUID patientId;
    private String cie10;
    private String severity;
    private String reaction;
}
