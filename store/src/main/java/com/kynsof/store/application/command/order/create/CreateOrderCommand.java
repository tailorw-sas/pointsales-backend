package com.kynsof.store.application.command.order.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.order.OrderDetailRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateOrderCommand implements ICommand {
    private List<OrderDetailRequest> orderDetails;
    private UUID customerId;
    private UUID id;

    public CreateOrderCommand(List<OrderDetailRequest> orderDetails, UUID customerId) {
        this.customerId = customerId;
        this.orderDetails = orderDetails;
    }

    public static CreateOrderCommand fromFrontRequest(OrderRequest request) {
        List<OrderDetailRequest> details = request.getOrderDetails().stream()
                .map(detail -> new OrderDetailRequest(detail.getProductId(), detail.getQuantity(), detail.getPrice()))
                .collect(Collectors.toList());

        return new CreateOrderCommand(
                details,
                request.getCustomerId()
        );
    }
    @Override
    public ICommandMessage getMessage() {
        return new CreateOrderMessage(id);
    }
}
