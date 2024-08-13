package com.kynsof.calendar.application.command.receipt.confirmPayment;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ConfirmPaymentReceiptMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CONFIRM_PAYMENT";

    public ConfirmPaymentReceiptMessage(UUID id) {
        this.id = id;
    }

}
