package com.kynsof.calendar.application.command.turn.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteTurnMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_TURN";

    public DeleteTurnMessage(UUID id) {
        this.id = id;
    }

}
