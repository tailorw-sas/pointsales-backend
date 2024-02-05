package com.kynsof.scheduled.application.command.service.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ServiceDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_SERVICE";

    public ServiceDeleteMessage(UUID id) {
        this.id = id;
    }

}
