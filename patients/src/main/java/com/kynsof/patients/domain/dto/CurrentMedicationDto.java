package com.kynsof.patients.domain.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentMedicationDto {
    private UUID id;
    private String name;
    private String dosage;
    @Enumerated(EnumType.STRING)
    private EStatusPatients status;
}
