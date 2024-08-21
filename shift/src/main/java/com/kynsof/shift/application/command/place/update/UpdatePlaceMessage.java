package com.kynsof.shift.application.command.place.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdatePlaceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_PATIENT";

    public UpdatePlaceMessage(UUID id) {
        this.id = id;
    }

}
