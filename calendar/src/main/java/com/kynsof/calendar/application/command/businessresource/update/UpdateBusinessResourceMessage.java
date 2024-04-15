package com.kynsof.calendar.application.command.businessresource.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBusinessResourceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_BUSINESS_RESOURCE";

    public UpdateBusinessResourceMessage(UUID id) {
        this.id = id;
    }

}
