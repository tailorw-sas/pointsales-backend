package com.kynsof.store.application.command.deleted;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.IOrderDetailService;
import org.springframework.stereotype.Component;

@Component
public class DeleteOrderDetailCommandHandler implements ICommandHandler<DeleteOrderDetailCommand> {

    private IOrderDetailService orderDetailService;

//    public DeleteOrderDetailCommandHandler(IOrderDetailService orderDetailService) {
//        this.orderDetailService = orderDetailService;
//    }


    @Override
    public void handle(DeleteOrderDetailCommand command) {
       // orderDetailService.deleteOrderDetail(command.getOrderDetailId());
    }
}
