package com.kynsof.treatments.application.command.businessProcedure.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateBusinessProcedureMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "CREATE_BUSINESS_SERVICES";

    public CreateBusinessProcedureMessage(boolean result) {
        this.result = result;
    }

}
