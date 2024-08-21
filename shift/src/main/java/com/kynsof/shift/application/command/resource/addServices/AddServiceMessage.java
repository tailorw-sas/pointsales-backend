package com.kynsof.shift.application.command.resource.addServices;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddServiceMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "ADD_SERVICES";

    public AddServiceMessage(UUID id) {
        this.id = id;
    }

}
