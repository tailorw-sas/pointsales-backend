package com.kynsof.patients.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
public class LocationHierarchyDto implements Serializable {
    private final ProvinceDto provinceDto;
    private final CantonDto cantonDto;
    private final ParroquiaDto parroquiaDto;
}
