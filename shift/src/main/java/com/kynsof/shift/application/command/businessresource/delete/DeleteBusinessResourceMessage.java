package com.kynsof.shift.application.command.businessresource.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteBusinessResourceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_BUSINESS_RESOURCE";

    public DeleteBusinessResourceMessage(UUID id) {
        this.id = id;
    }

}
