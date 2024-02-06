package com.kynsof.patients.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalInformationUpdateDto {
    private UUID id;
    private String bloodType;
    private String medicalHistory;
    private com.kynsof.patients.domain.dto.Status Status;

}
