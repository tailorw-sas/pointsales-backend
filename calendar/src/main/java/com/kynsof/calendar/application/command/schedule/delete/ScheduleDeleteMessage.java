package com.kynsof.calendar.application.command.schedule.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ScheduleDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_SCHEDULE";

    public ScheduleDeleteMessage(UUID id) {
        this.id = id;
    }

}
