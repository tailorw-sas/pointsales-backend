package com.kynsof.store.application.command.create;


import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateOrderDetailsCommand implements ICommand {
    private final UUID orderId;
    private final UUID productId;
    private final Integer quantity;
    private final Double price;
    private UUID id;

    public CreateOrderDetailsCommand(UUID orderId, UUID productId, Integer quantity, Double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateOrderDetailMessage(id);
    }


}

