package com.kynsof.treatments.application.query.vaccine.getall;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.dto.enumDto.RouteOfAdministration;
import com.kynsof.treatments.domain.dto.enumDto.VaccineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class VaccineResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private VaccineType type;
    private double minAge;
    private double maxAge;
    private UUID serviceId;
    private RouteOfAdministration routeOfAdministration;
    private String preventableDiseases;
    private String dose;


    public VaccineResponse(VaccineDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.type = dto.getType();
        this.minAge = dto.getMinAge();
        this.maxAge = dto.getMaxAge();
        this.serviceId = dto.getServiceId();
        this.routeOfAdministration = dto.getRouteOfAdministration();
        this.preventableDiseases = dto.getPreventableDiseases();
        this.dose = dto.getDose();
    }

}