package com.kynsof.scheduled.domain.dto;

import com.kynsof.scheduled.domain.dto.enumType.GeographicLocationType;
import com.kynsof.scheduled.infrastructure.config.bus.query.IResponse;
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