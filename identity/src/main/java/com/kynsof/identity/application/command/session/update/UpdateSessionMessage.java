package com.kynsof.identity.application.command.session.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateSessionMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_SESSION";

    public UpdateSessionMessage(UUID id) {
        this.id = id;
    }
}
