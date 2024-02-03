package com.kynsof.scheduled.application.command.business.create;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateBusinessMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_BUSINESS";

    public CreateBusinessMessage(UUID id) {
        this.id = id;
    }

}
