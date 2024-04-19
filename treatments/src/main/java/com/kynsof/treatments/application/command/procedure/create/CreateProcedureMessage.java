package com.kynsof.treatments.application.command.procedure.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateProcedureMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_PROCEDURE";

    public CreateProcedureMessage(UUID id) {
        this.id = id;
    }

}
