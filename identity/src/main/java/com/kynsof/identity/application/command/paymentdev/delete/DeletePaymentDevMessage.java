package com.kynsof.identity.application.command.paymentdev.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeletePaymentDevMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_PAYMENT_DEV";

    public DeletePaymentDevMessage(UUID id) {
        this.id = id;
    }

}
