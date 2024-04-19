package com.kynsof.treatments.application.command.procedure.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateProcedureMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_PROCEDURE";

    public UpdateProcedureMessage(UUID id) {
        this.id = id;
    }

}
