package com.kynsof.treatments.application.command.externalConsultation.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TreatmentRequest {
    private String description;
    private UUID medication;
    private int quantity;
    private String medicineUnit;
}
