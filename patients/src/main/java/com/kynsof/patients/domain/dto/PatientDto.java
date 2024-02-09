package com.kynsof.patients.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PatientDto implements Serializable {

    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private Status status;
    private LocalDate birthDate;
}
