package com.kynsof.calendar.application.command.turn.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateTurnMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_TURN";

    public CreateTurnMessage(UUID id) {
        this.id = id;
    }

}
