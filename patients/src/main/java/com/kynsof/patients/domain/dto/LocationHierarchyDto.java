package com.kynsof.patients.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class LocationHierarchyDto implements Serializable {
    private final UUID provinceId;
    private final UUID cantonId;
}
