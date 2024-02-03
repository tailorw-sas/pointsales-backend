package com.kynsof.scheduled.application.command.business.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class BusinessDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_BUSINESS";

    public BusinessDeleteMessage(UUID id) {
        this.id = id;
    }

}
