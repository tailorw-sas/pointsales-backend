package com.kynsof.patients.application.query.geographicLocation.findCantonAndProvinceIdsByParroquiaId;

import com.kynsof.patients.domain.dto.LocationHierarchyDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LocationHierarchyResponse implements IResponse, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private  UUID provinceId;
    private  UUID cantonId;

    public LocationHierarchyResponse(LocationHierarchyDto contactInfoDto) {
        this.provinceId = contactInfoDto.getProvinceId();
        this.cantonId = contactInfoDto.getCantonId();

    }
}
