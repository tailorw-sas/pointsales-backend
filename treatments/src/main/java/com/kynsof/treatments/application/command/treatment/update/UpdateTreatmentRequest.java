package com.kynsof.treatments.application.command.treatment.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTreatmentRequest {
    private String description;
    private String medication;
    private String dose;
    private String frequency;
    private String duration;
}
