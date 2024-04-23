package com.kynsof.treatments.application.command.treatment.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTreatmentRequest {
    private String description;
    private String medication;
    private String dose;
    private String frequency;
    private String duration;
    private UUID idExternalConsultation;
}
