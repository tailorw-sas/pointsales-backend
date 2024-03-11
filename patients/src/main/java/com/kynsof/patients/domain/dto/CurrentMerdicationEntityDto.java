package com.kynsof.patients.domain.dto;

import com.kynsof.patients.domain.dto.enumTye.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentMerdicationEntityDto {
    private UUID id;
    private String name;
    private String dosage;
    private Status status;
    private UUID MedicalInformationId;
    private MedicalInformationDto medicalInformationDto;
}
