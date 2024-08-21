package com.kynsof.shift.application.command.businessresource.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateBusinessResourceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_BUSINESS_RESOURCE";

    public CreateBusinessResourceMessage(UUID id) {
        this.id = id;
    }

}
