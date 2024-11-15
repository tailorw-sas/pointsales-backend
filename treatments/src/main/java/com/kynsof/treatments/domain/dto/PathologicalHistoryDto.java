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
public class PathologicalHistoryDto {

    private UUID id;
    private PatientDto patient;
    private Cie10Dto cie10;
    private String status;
    private String observations;
    private String type;
    private LocalDateTime createdAt;

    public PathologicalHistoryDto(UUID id, PatientDto patient, Cie10Dto cie10, String observations,
                                  String status, String type) {
        this.id = id;
        this.patient = patient;
        this.cie10 = cie10;
        this.observations = observations;
        this.status = status;
        this.type = type;
    }
}