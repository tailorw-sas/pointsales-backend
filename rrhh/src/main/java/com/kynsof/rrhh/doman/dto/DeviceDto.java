package com.kynsof.rrhh.doman.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeviceDto {
    private UUID id;
    private String serialId;
    private String ip;
    private BusinessDto business;
    private boolean deleted;

    public DeviceDto(UUID id, String serialId, String ip, BusinessDto business) {
        this.id = id;
        this.serialId = serialId;
        this.ip = ip;
        this.business = business;
    }

}
