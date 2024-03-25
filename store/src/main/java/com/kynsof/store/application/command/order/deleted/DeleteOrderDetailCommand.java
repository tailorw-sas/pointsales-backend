package com.kynsof.store.application.command.order.deleted;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteOrderDetailCommand implements ICommand {
    private final UUID orderDetailId;

    public DeleteOrderDetailCommand(UUID orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }

}
