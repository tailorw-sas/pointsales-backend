package com.kynsof.store.application.command.order.update;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.IOrderDetailService;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderDetailsCommandHandler implements ICommandHandler<UpdateOrderDetailsCommand> {

    private IOrderDetailService orderDetailService;

//    @Autowired
//    public UpdateOrderDetailCommandHandler(IOrderDetailService orderDetailService) {
//        this.orderDetailService = orderDetailService;
//    }

    @Override
    public void handle(UpdateOrderDetailsCommand command) {
//        OrderDetailDto orderDetailDto = new OrderDetailDto(
//                command.getOrderDetailId(),
//                command.getOrderId(),
//                command.getProductId(),
//                command.getQuantity(),
//                command.getPrice()
//        );
//        orderDetailService.updateOrderDetail(orderDetailDto);
    }
}

