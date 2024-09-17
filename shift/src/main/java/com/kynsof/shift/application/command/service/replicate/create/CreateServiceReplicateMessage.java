package com.kynsof.shift.application.command.service.replicate.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateServiceReplicateMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_SERVICE";

    public CreateServiceReplicateMessage(UUID id) {
        this.id = id;
    }

}
