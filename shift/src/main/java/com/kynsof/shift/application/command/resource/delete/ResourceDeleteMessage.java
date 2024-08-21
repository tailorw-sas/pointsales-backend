package com.kynsof.shift.application.command.resource.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ResourceDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_RESOURCE";

    public ResourceDeleteMessage(UUID id) {
        this.id = id;
    }

}
