package com.kynsof.treatments.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}