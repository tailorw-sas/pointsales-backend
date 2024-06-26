package com.kynsof.calendar.application.command.block.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateBlockMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_BLOCK";

    public CreateBlockMessage(UUID id) {
        this.id = id;
    }

}
