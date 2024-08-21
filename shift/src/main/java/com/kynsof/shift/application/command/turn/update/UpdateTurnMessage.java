package com.kynsof.shift.application.command.turn.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateTurnMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_PATIENT";

    public UpdateTurnMessage(UUID id) {
        this.id = id;
    }

}
