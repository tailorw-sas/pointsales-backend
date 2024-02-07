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
    private String medication;
    private String dose;
    private String frequency;
    private String duration;
}