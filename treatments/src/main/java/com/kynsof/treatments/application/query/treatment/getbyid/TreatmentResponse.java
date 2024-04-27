package com.kynsof.treatments.application.query.treatment.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.MedicineUnit;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TreatmentResponse implements IResponse {
    private UUID id;
    private String description;
    private String medication;
    private int quantity;
    private MedicineUnit medicineUnit;

    public TreatmentResponse(TreatmentDto treatmentDto) {
        this.id = treatmentDto.getId();
        this.description = treatmentDto.getDescription();
        this.medication = treatmentDto.getMedication();
        this.quantity = treatmentDto.getQuantity();
        this.medicineUnit = treatmentDto.getMedicineUnit();
    }

}
