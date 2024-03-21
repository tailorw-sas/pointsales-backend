package com.kynsof.patients.application.query.geographicLocation.findCantonAndProvinceIdsByParroquiaId;

import com.kynsof.patients.domain.dto.CantonDto;
import com.kynsof.patients.domain.dto.LocationHierarchyDto;
import com.kynsof.patients.domain.dto.ParroquiaDto;
import com.kynsof.patients.domain.dto.ProvinceDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LocationHierarchyResponse implements IResponse, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ProvinceDto provinceDto;
    private final CantonDto cantonDto;
    private final ParroquiaDto parroquiaDto;

    public LocationHierarchyResponse(LocationHierarchyDto contactInfoDto) {
        this.provinceDto = contactInfoDto.getProvinceDto();
        this.cantonDto = contactInfoDto.getCantonDto();
        this.parroquiaDto = contactInfoDto.getParroquiaDto();

    }
}
