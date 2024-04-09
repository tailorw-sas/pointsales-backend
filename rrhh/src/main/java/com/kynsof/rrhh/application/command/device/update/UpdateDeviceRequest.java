package com.kynsof.rrhh.application.command.device.update;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDeviceRequest {
    private String serialId;
    private String ip;
    private UUID businessId;
}
