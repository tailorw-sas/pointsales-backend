package com.kynsof.treatments.application.command.treatment.createall;

import com.kynsof.treatments.domain.dto.MedicineUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllTreatmentRequest {
    private String description;
    private String medication;
    private String quantity;
    private MedicineUnit medicineUnit;
}
