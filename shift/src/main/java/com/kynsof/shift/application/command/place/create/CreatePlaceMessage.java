package com.kynsof.shift.application.command.place.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatePlaceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_SERVICE_TYPE";

    public CreatePlaceMessage(UUID id) {
        this.id = id;
    }

}
