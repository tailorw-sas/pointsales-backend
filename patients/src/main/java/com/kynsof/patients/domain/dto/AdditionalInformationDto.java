package com.kynsof.patients.domain.dto;

import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.infrastructure.entity.Patients;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AdditionalInformationDto {
    private UUID id;

    private Patients patient;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private Status status;
}
