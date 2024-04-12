package com.kynsof.treatments.application.command.vaccine.create;

import com.kynsof.treatments.domain.dto.enumDto.RouteOfAdministration;
import com.kynsof.treatments.domain.dto.enumDto.VaccineType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateVaccineRequest {
    private String name;
    private String description;
    private VaccineType type;
    private double minAge;
    private double maxAge;
    private String dose;
    private RouteOfAdministration routeOfAdministration;
    private String preventableDiseases;
    private UUID serviceId;
}
