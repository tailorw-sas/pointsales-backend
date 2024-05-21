package com.kynsof.identity.application.command.paymentdev.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdatePaymentDevMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_PAYMENT_DEV";

    public UpdatePaymentDevMessage(UUID id) {
        this.id = id;
    }

}
