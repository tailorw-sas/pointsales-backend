package com.kynsof.rrhh.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeviceDto {
    private UUID id;
    private String serialId;
    private String ip;
    private BusinessDto business;
}
