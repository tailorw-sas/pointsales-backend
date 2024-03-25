package com.kynsof.treatments.domain.dto;

import com.kynsof.treatments.domain.dto.enumDto.RouteOfAdministration;
import com.kynsof.treatments.domain.dto.enumDto.VaccineType;
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
    private double minAge;
    private double maxAge;
    private String dose;
    private RouteOfAdministration routeOfAdministration;
    private String preventableDiseases;
}
