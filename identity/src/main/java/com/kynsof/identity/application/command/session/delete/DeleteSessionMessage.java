package com.kynsof.identity.application.command.session.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteSessionMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_SESSION";

    public DeleteSessionMessage(UUID id) {
        this.id = id;
    }
}
