package com.kynsof.rrhh.application.query.device.getbyid;

import com.kynsof.rrhh.doman.dto.BusinessDto;
import com.kynsof.rrhh.doman.dto.DeviceDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class DeviceResponse implements IResponse {
    private UUID id;
    private String serialId;
    private String ip;
    private BusinessDto business;

    public DeviceResponse(DeviceDto object) {
        this.id = object.getId();
        this.serialId = object.getSerialId();
        this.ip = object.getIp();
        this.business = object.getBusiness();
    }

}