package com.kynsof.scheduled.application.command.receipt.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReceiptDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_RECEIPT";

    public ReceiptDeleteMessage(UUID id) {
        this.id = id;
    }

}
