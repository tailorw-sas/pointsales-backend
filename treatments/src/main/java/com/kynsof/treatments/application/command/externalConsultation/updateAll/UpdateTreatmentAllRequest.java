package com.kynsof.treatments.application.command.externalConsultation.updateAll;

import com.kynsof.treatments.domain.dto.MedicineUnit;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTreatmentAllRequest {
    private String description;
    private UUID medication;
    private int quantity;
    private MedicineUnit medicineUnit;
}
