package com.kynsof.calendar.application.command.businessservices.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBusinessServicesMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_BUSINESS_SERVICE";

    public UpdateBusinessServicesMessage(UUID id) {
        this.id = id;
    }

}
