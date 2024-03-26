package com.kynsof.store.application.command.order.update;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.UpdateOrderDetailRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateOrderDetailsCommand implements ICommand {
    private final UUID orderDetailId;
    private final UUID orderId;
    private final UUID productId;
    private final Integer quantity;
    private final Double price;

    public UpdateOrderDetailsCommand(UUID orderDetailId, UUID orderId, UUID productId, Integer quantity, Double price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    public static UpdateOrderDetailsCommand fromRequest(UUID orderDetailId, UpdateOrderDetailRequest request) {
        return new UpdateOrderDetailsCommand(
                orderDetailId,
                request.getOrderId(),
                request.getProductId(),
                request.getQuantity(),
                request.getPrice()
        );
    }
    @Override
    public ICommandMessage getMessage() {
        return new UpdateOrderDetailMessage();
    }

}
