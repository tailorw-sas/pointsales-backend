package com.kynsof.store.application.command.order.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateOrderMessage implements ICommandMessage {
    private final UUID id;
    private final String command = "CREATE_ORDER";

    public CreateOrderMessage(UUID id) {
        this.id = id;
    }
}
