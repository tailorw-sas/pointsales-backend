package com.kynsof.treatments.application.command.treatment.create;

import java.util.UUID;

import com.kynsof.treatments.domain.dto.MedicineUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTreatmentRequest {
    private String description;
    private String medication;
    private String quantity;
    private MedicineUnit duration;
    private UUID externalConsultation;
}
