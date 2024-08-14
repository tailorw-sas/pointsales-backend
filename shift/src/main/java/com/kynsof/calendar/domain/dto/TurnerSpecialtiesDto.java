package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.domain.dto.enumType.ETurnerSpecialtiesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TurnerSpecialtiesDto {

    private UUID id;
    private String medicalHistory;
    private String patient;
    private String identification;
    private ResourceDto resource;//Doctor
    private ServiceDto service;//Specialties
    private ETurnerSpecialtiesStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
