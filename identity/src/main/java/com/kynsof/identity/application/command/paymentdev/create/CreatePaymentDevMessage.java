package com.kynsof.identity.application.command.paymentdev.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatePaymentDevMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_PAYMENT_DEV";

    public CreatePaymentDevMessage(UUID id) {
        this.id = id;
    }

}
