package com.kynsoft.rrhh.application.command.device.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateDeviceRequest {
    private String serialId;
    private String ip;
    private UUID businessId;
}
