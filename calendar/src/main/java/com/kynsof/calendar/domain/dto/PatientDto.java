package com.kynsof.calendar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements Serializable {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private PatientStatus status;
}