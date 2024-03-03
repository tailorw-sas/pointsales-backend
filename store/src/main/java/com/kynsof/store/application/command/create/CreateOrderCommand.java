package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.request.OrderDetailRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class CreateOrderCommand implements ICommand {
    private UUID supplierId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderDetailRequest> orderDetails;

    public CreateOrderCommand(UUID supplierId, LocalDateTime orderDate, String status, List<OrderDetailRequest> orderDetails) {
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderDetails = orderDetails;
    }



    // ICommand implementation
    @Override
    public ICommandMessage getMessage() {
        // Logic to create and return an ICommandMessage
        return null;
    }
}
