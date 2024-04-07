package com.kynsof.calendar.application.command.schedule.createlote;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateScheduleByLoteMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "CREATE_SCHEDULE_BY_LOTE";

    public CreateScheduleByLoteMessage() {
        this.result = true;
    }

}
