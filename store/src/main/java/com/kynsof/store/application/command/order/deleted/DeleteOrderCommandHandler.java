package com.kynsof.store.application.command.order.deleted;



import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.IOrderService;
import org.springframework.stereotype.Component;

@Component
public class DeleteOrderCommandHandler implements ICommandHandler<DeleteOrderCommand> {


    private IOrderService orderService;

//    public DeleteOrderCommandHandler(IOrderService orderService) {
//        this.orderService = orderService;
//    }

    @Override
    public void handle(DeleteOrderCommand command) {
        //  orderService.deleteOrder(command.getOrderId());
    }
}

