package com.kynsof.store.application.command.order.create;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.IOrderDetailService;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderDetailsCommandHandler implements ICommandHandler<CreateOrderDetailsCommand> {

    private IOrderDetailService orderDetailService;

//    @Autowired
//    public CreateOrderDetailCommandHandler(IOrderDetailService orderDetailService) {
//        this.orderDetailService = orderDetailService;
//    }

    @Override
    public void handle(CreateOrderDetailsCommand command) {
//        OrderDetailDto orderDetailDto = new OrderDetailDto(
//                null, // El ID será generado por la base de datos o el servicio
//                command.getOrderId(),
//                command.getProductId(),
//                command.getQuantity(),
//                command.getPrice()
//        );
//        orderDetailService.createOrderDetail(orderDetailDto);
    }
}
