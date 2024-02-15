package com.kynsof.scheduled.application.command.resource.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateResourceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_RESOURCE";

    public CreateResourceMessage(UUID id) {
        this.id = id;
    }

}
