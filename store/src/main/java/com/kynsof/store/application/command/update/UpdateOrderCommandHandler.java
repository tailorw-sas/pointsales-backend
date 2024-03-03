package com.kynsof.store.application.command.update;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderCommandHandler implements ICommandHandler<UpdateOrderCommand> {

//    private final IOrderService orderService;

    public UpdateOrderCommandHandler() {
    }

    @Override
    public void handle(UpdateOrderCommand command) {
//        // Convert command to DTO and call the service for update
//        OrderDto orderDto = // Conversion logic
//                orderService.updateOrder(orderDto);
    }
}
