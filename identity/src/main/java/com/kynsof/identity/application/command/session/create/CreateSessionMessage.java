package com.kynsof.identity.application.command.session.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateSessionMessage implements ICommandMessage {

    private final UUID id;

    private final String message = "CREATE_SESSION";

    public CreateSessionMessage(UUID id) {
        this.id = id;
    }
}
