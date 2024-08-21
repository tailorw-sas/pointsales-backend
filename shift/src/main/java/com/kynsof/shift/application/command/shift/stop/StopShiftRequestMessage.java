package com.kynsof.shift.application.command.shift.stop;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StopShiftRequestMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "SHIFT_CREATED";

    public StopShiftRequestMessage(UUID id) {
        this.id = id;
    }

}
