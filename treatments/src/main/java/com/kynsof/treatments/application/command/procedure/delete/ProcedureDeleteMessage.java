package com.kynsof.treatments.application.command.procedure.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProcedureDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PROCEDURE";

    public ProcedureDeleteMessage(UUID id) {
        this.id = id;
    }

}
