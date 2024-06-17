package com.kynsoft.rrhh.application.command.device.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateDeviceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_DEVICE";

    public UpdateDeviceMessage(UUID id) {
        this.id = id;
    }

}
