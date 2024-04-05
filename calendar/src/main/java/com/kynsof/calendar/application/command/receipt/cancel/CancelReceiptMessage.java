package com.kynsof.calendar.application.command.receipt.cancel;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CancelReceiptMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CANCEL_RECEIPT";

    public CancelReceiptMessage(UUID id) {
        this.id = id;
    }

}
