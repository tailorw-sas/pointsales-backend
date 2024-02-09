package com.kynsof.patients.domain.dto;

import com.kynsof.patients.domain.dto.enumTye.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class DependentPatientDto {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private Status status;
    private PatientDto prime;
}
