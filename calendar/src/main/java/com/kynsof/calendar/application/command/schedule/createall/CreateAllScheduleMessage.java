package com.kynsof.calendar.application.command.schedule.createall;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateAllScheduleMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "CREATE_SCHEDULE";

    public CreateAllScheduleMessage() {
        this.result = true;
    }

}
