package com.kynsof.calendar.application.command.replicateObject;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateReplicateMessage implements ICommandMessage {

    private final String command = "TO_REPLICATE";

    public CreateReplicateMessage() {
    }

}
