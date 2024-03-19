package com.kynsof.patients.domain.dto;

import com.kynsof.patients.domain.dto.enumTye.GeographicLocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GeographicLocationDto implements Serializable {
    private UUID id;
    private String name;
    private GeographicLocationType type;
    private GeographicLocationDto parent;
}
