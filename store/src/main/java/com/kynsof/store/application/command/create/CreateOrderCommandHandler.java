package com.kynsof.store.application.command.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderCommandHandler implements ICommandHandler<CreateOrderCommand> {

//    private final IOrderService orderService;


    @Override
    public void handle(CreateOrderCommand command) {
//        List<OrderDetailDto> orderDetails = command.getOrderDetails().stream()
//                .map(detail -> new OrderDetailDto(
//                        detail.getProductId(),
//                        detail.getQuantity(),
//                        detail.getPrice()
//                )).collect(Collectors.toList());
//
//        UUID id = orderService.create(new OrderDto(
//                command.getSupplierId(),
//                command.getOrderDate(),
//                command.getStatus(),
//                orderDetails
//        ));
//        command.setId(id);
    }
}
