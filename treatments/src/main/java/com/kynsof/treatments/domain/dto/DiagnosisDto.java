package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
public class DiagnosisDto {
    private UUID id;
    private String icdCode; // Código CIE-10
    private String description;
}
