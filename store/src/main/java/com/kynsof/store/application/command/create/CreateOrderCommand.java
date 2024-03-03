package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.OrderDetailRequest;
import com.kynsof.store.application.command.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateOrderCommand implements ICommand {
    private UUID supplierId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderDetailRequest> orderDetails;
    private UUID id;

    public CreateOrderCommand(UUID supplierId, LocalDateTime orderDate, String status, List<OrderDetailRequest> orderDetails) {
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public static CreateOrderCommand fromFrontRequest(OrderRequest request) {
        List<OrderDetailRequest> details = request.getOrderDetails().stream()
                .map(detail -> new OrderDetailRequest(detail.getProductId(), detail.getQuantity(), detail.getPrice()))
                .collect(Collectors.toList());

        return new CreateOrderCommand(
                request.getSupplierId(),
                request.getOrderDate(),
                request.getStatus(),
                details
        );
    }
    @Override
    public ICommandMessage getMessage() {
        return new CreateOrderMessage(id);
    }
}
