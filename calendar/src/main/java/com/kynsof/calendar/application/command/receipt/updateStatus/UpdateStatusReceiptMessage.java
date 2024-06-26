package com.kynsof.calendar.application.command.receipt.updateStatus;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateStatusReceiptMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_RECEIPT";

    public UpdateStatusReceiptMessage(UUID id) {
        this.id = id;
    }

}
