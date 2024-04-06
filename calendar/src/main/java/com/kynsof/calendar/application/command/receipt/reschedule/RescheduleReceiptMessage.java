package com.kynsof.calendar.application.command.receipt.reschedule;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RescheduleReceiptMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CANCEL_RECEIPT";

    public RescheduleReceiptMessage(UUID id) {
        this.id = id;
    }

}
