package com.kynsof.store.application.command.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateOrderDetailMessage implements ICommandMessage {
    private final String command = "UPDATE_ORDER_DETAIL";

    public UpdateOrderDetailMessage() {
    }
}
