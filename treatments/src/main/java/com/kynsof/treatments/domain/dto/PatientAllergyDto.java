package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientAllergyDto {

    private UUID id;
    private PatientDto patient;
    private Cie10Dto cie10;
    private String severity;
    private String reaction;
    private String status;
    private LocalDateTime createdAt;

    public PatientAllergyDto(UUID id, PatientDto patient, Cie10Dto cie10, String severity, String reaction,
                             String status) {
        this.id = id;
        this.patient = patient;
        this.cie10 = cie10;
        this.severity = severity;
        this.reaction = reaction;
        this.status = status;
    }
}