package com.kynsof.treatments.application.command.typeService.replicate.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateServiceTypeReplicateMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_SERVICE_TYPE";

    public CreateServiceTypeReplicateMessage(UUID id) {
        this.id = id;
    }

}
