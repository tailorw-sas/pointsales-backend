package com.kynsof.rrhh.application.command.users.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateUserMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_USER";

    public CreateUserMessage(UUID id) {
        this.id = id;
    }

}
