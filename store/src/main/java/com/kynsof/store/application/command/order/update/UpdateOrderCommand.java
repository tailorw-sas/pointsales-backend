package com.kynsof.store.application.command.order.update;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.order.OrderDetailRequest;
import com.kynsof.store.application.command.order.create.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateOrderCommand implements ICommand {
    private final UUID orderId;
    private final List<OrderDetailRequest> orderDetails;

    public UpdateOrderCommand(UUID orderId, List<OrderDetailRequest> orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public static UpdateOrderCommand fromRequest(UUID orderId, OrderRequest request) {
        return new UpdateOrderCommand(
                orderId,
                request.getOrderDetails()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateOrderMessage();
    }
}

