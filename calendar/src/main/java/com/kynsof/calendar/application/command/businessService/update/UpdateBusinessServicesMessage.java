package com.kynsof.calendar.application.command.businessService.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateBusinessServicesMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "UPDATE_BUSINESS_SERVICE";

    public UpdateBusinessServicesMessage(boolean result) {
        this.result = result;
    }

}
