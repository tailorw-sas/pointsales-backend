package com.kynsof.store.application.command.order.deleted;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteOrderCommand implements ICommand {
    private final UUID orderId;

    public DeleteOrderCommand(UUID orderId) {
        this.orderId = orderId;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
