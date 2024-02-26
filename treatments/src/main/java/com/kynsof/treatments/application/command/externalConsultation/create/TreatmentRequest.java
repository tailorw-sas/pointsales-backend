package com.kynsof.treatments.application.command.externalConsultation.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreatmentRequest {
    private String description;
    private String medication;
    private String dose;
    private String frequency;
    private String duration;
}
