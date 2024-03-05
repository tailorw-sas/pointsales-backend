package com.kynsof.store.application.command.customer.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCustomerMessage implements ICommandMessage {
    private final UUID id;
    private final String command = "CREATE_CATEGORY";

    public CreateCustomerMessage(UUID id) {
        this.id = id;
    }
}
