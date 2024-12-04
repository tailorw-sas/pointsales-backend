package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class TreatmentDto {

    private UUID id;
    private String description;
    private MedicinesDto medication;
    private int quantity;
    private String medicineUnit;

    private ExternalConsultationDto externalConsultationDto;

    public TreatmentDto(UUID id, String description, MedicinesDto medication, int quantity, String duration) {
        this.id = id;
        this.description = description;
        this.medication = medication;
        this.quantity = quantity;
        this.medicineUnit = duration;
    }

}
