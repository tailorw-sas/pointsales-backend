package com.kynsof.patients.application.query.geographicLocation.getall;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.enumTye.GeographicLocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class GeographicLocationResponse implements IResponse {
    private UUID id;
    private String name;
    private GeographicLocationType type;
     private GeographicLocationDto parent;

    public GeographicLocationResponse(GeographicLocationDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.name = contactInfoDto.getName();
        this.type = contactInfoDto.getType();
        this.parent = contactInfoDto.getParent();
    }

}