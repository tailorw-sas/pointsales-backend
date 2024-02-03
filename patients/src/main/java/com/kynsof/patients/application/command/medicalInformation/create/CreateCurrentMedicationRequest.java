package com.kynsof.patients.application.command.medicalInformation.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCurrentMedicationRequest {
    private String name;
    private String description;
}
