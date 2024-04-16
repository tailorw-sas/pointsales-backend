package com.kynsof.calendar.application.command.businessservices.createall;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateAllBusinessServicesMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "CREATE_BUSINESS_SERVICES";

    public CreateAllBusinessServicesMessage(boolean result) {
        this.result = result;
    }

}
