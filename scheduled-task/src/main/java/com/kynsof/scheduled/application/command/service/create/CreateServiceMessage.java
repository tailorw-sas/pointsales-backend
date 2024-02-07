package com.kynsof.scheduled.application.command.service.create;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateServiceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_SERVICE";

    public CreateServiceMessage(UUID id) {
        this.id = id;
    }

}