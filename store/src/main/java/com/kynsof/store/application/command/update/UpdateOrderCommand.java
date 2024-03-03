package com.kynsof.store.application.command.update;



import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.request.OrderDetailRequest;
import com.kynsof.store.application.request.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateOrderCommand implements ICommand {
    private final UUID orderId;
    private final UUID supplierId;
    private final LocalDateTime orderDate;
    private final String status;
    private final List<OrderDetailRequest> orderDetails;

    public UpdateOrderCommand(UUID orderId, UUID supplierId, LocalDateTime orderDate, String status, List<OrderDetailRequest> orderDetails) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public static UpdateOrderCommand fromRequest(UUID orderId, OrderRequest request) {
        return new UpdateOrderCommand(
                orderId,
                request.getSupplierId(),
                request.getOrderDate(),
                request.getStatus(),
                request.getOrderDetails()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        // Logic to create and return an ICommandMessage
        return null;
    }
}

