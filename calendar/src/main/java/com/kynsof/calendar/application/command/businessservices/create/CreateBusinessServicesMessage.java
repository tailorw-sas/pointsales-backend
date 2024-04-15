package com.kynsof.calendar.application.command.businessservices.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateBusinessServicesMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_BUSINESS_SERVICE";

    public CreateBusinessServicesMessage(UUID id) {
        this.id = id;
    }

}
