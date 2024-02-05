package com.kynsof.patients.domain.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PatientDto {

    private UUID id;

    private String identification;

    private String name;

    private String lastName;

    private String gender;

    @Enumerated(EnumType.STRING)
    private Status status;
}
