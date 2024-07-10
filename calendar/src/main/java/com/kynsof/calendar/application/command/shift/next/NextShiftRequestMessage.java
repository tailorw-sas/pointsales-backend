package com.kynsof.calendar.application.command.shift.next;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class NextShiftRequestMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "SHIFT_CREATED";

    public NextShiftRequestMessage(UUID id) {
        this.id = id;
    }

}
