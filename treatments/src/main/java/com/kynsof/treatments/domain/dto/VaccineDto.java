package com.kynsof.treatments.domain.dto;

import com.kynsof.treatments.domain.enumDto.VaccineType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class VaccineDto {
    private UUID id;
    private String name;
    private String description;
    private VaccineType type;
    private Integer minAge;
    private Integer maxAge;
}
