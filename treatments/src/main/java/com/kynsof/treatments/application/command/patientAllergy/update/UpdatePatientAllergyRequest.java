package com.kynsof.treatments.application.command.patientAllergy.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePatientAllergyRequest {
    private UUID patientId;
    private String cie10;
    private String observations;
    private String status;
    private  String type;

}
