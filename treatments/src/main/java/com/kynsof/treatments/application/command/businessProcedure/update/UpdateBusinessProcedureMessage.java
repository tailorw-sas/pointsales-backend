package com.kynsof.treatments.application.command.businessProcedure.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateBusinessProcedureMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "UPDATE_BUSINESS_SERVICE";

    public UpdateBusinessProcedureMessage(boolean result) {
        this.result = result;
    }

}
