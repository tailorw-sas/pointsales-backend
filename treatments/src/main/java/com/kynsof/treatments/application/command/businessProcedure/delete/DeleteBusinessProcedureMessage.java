package com.kynsof.treatments.application.command.businessProcedure.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteBusinessProcedureMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_BUSINESS_MODULE";

    public DeleteBusinessProcedureMessage(UUID id) {
        this.id = id;
    }

}
