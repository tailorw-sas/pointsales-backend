package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateScheduleMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_SCHEDULE";

    public UpdateScheduleMessage(UUID id) {
        this.id = id;
    }

}
