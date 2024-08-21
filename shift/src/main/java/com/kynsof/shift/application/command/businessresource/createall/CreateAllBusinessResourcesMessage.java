package com.kynsof.shift.application.command.businessresource.createall;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateAllBusinessResourcesMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "CREATE_BUSINESS_RESOURCES";

    public CreateAllBusinessResourcesMessage(boolean result) {
        this.result = result;
    }

}
