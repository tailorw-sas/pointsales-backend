package com.kynsof.rrhh.application.command.users.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateUserMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_USER";

    public UpdateUserMessage(UUID id) {
        this.id = id;
    }

}
