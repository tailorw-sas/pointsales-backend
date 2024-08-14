package com.kynsof.calendar.application.command.receipt.createReceiptScheduled;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateScheduleReceiptMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_RECEIPT_SCHEDULED";

    public CreateScheduleReceiptMessage(UUID id) {
        this.id = id;
    }

}
