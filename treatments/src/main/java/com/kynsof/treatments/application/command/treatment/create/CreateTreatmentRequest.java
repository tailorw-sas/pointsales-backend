package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.treatments.domain.dto.MedicineUnit;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreateTreatmentRequest {
    private String description;
    private String medication;
    private int quantity;
    private MedicineUnit duration;
    private UUID externalConsultation;
}
