package com.kynsoft.rrhh.application.command.device.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateDeviceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_DEVICE";

    public CreateDeviceMessage(UUID id) {
        this.id = id;
    }

}
