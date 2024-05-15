package com.kynsof.calendar.application.command.schedule.createGoogleStyle;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateScheduleByLoteGoogleStyleMessage implements ICommandMessage {

    private final String command = "CREATE_SCHEDULE_BY_LOTE";

    public CreateScheduleByLoteGoogleStyleMessage() {
    }

}
