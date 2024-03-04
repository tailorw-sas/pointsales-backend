package com.kynsof.store.application.command.order.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateOrderMessage implements ICommandMessage {
    private final String command = "UPDATE_ORDER";

    public UpdateOrderMessage() {
    }
}
