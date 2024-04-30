package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.treatments.domain.dto.MedicineUnit;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTreatmentRequest {
    private String description;
    private String medication;
    private int quantity;
    private MedicineUnit medicineUnit;
    private UUID externalConsultation;
}
