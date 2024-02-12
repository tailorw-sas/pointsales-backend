package com.kynsof.scheduled.domain.dto;

import com.kynsof.scheduled.domain.dto.enumType.GeographicLocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GeographicLocationDto {
    private UUID id;
    private String name;
    private GeographicLocationType type;
    private GeographicLocationDto parent;
}