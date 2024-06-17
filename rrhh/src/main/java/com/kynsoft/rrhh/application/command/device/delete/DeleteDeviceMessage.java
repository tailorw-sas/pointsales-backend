package com.kynsoft.rrhh.application.command.device.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteDeviceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_DEVICE";

    public DeleteDeviceMessage(UUID id) {
        this.id = id;
    }

}
