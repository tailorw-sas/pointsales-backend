package com.kynsof.treatments.application.command.treatment.createall;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllTreatmentRequest {
    private String description;
    private String medication;
    private String quantity;
    private String duration;
}
