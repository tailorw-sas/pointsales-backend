package com.kynsof.rrhh.application.command.device.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeviceRequest {
    private String serialId;
    private String ip;
    private UUID businessId;
}
