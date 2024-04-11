package com.kynsof.calendar.application.command.receipt.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateReceiptMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CANCEL_RECEIPT";

    public UpdateReceiptMessage(UUID id) {
        this.id = id;
    }

}
