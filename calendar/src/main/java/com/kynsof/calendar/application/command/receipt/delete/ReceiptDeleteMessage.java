package com.kynsof.calendar.application.command.receipt.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
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
