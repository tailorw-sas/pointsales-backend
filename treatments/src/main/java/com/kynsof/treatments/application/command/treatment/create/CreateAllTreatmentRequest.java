package com.kynsof.treatments.application.command.treatment.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllTreatmentRequest {
    private String description;
    private String medication;
    private int quantity;
    private String medicineUnit;
}
