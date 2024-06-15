package com.kynsof.rrhh.application.command.device.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateDeviceRequest {
    private String serialId;
    private String ip;
    private UUID businessId;
}
