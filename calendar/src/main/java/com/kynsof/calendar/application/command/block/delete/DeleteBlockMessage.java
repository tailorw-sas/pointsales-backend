package com.kynsof.calendar.application.command.block.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteBlockMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_BLOCK";

    public DeleteBlockMessage(UUID id) {
        this.id = id;
    }

}
