package com.kynsof.scheduled.application.command.schedule.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateScheduleMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_SCHEDULE";

    public CreateScheduleMessage(UUID id) {
        this.id = id;
    }

}
