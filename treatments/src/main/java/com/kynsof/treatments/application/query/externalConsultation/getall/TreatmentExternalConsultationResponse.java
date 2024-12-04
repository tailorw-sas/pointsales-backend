package com.kynsof.treatments.application.query.externalConsultation.getall;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TreatmentExternalConsultationResponse implements IResponse {
    private UUID id;
    private String description;
    private MedicinesDto medication;
    private int quantity;
    private String medicineUnit;

    public TreatmentExternalConsultationResponse(TreatmentDto treatmentDto) {
        this.id = treatmentDto.getId();
        this.description = treatmentDto.getDescription();
        this.medication = treatmentDto.getMedication() != null ? treatmentDto.getMedication() : null;
        this.quantity = treatmentDto.getQuantity();
        this.medicineUnit = treatmentDto.getMedicineUnit();
    }

}
