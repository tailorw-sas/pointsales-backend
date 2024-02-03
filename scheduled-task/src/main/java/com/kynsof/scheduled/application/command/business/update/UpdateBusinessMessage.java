package com.kynsof.scheduled.application.command.business.update;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBusinessMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_BUSINESS";

    public UpdateBusinessMessage(UUID id) {
        this.id = id;
    }

}
