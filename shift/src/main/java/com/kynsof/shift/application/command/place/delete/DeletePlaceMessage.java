package com.kynsof.shift.application.command.place.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeletePlaceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_QUALIFICATION";

    public DeletePlaceMessage(UUID id) {
        this.id = id;
    }

}
