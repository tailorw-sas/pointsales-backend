package com.kynsof.shift.domain.dto;

import com.kynsof.shift.domain.dto.enumType.ETurnerSpecialtiesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalDateTime shiftDateTime;
    private LocalTime consultationTime;
    private  BusinessDto business;

    public TurnerSpecialtiesDto(UUID id, String medicalHistory, String patient, String identification,
                                ResourceDto resource, ServiceDto service, ETurnerSpecialtiesStatus status,
                                LocalDateTime shiftDateTime, LocalTime consultationTime, BusinessDto business) {
        this.id = id;
        this.medicalHistory = medicalHistory;
        this.patient = patient;
        this.identification = identification;
        this.resource = resource;
        this.service = service;
        this.status = status;
        this.shiftDateTime = shiftDateTime;
        this.consultationTime = consultationTime;
        this.business = business;
    }
}
