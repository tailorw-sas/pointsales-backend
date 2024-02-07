package com.kynsof.scheduled.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private PatientStatus status;
}