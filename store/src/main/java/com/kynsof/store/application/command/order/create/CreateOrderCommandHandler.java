package com.kynsof.store.application.command.order.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.CustomerDto;
import com.kynsof.store.domain.dto.OrderDetailDto;
import com.kynsof.store.domain.dto.OrderEntityDto;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.services.ICustomerService;
import com.kynsof.store.domain.services.IOrderService;
import com.kynsof.store.domain.services.IProductService;
import com.kynsof.store.infrastructure.enumDto.OrderDetailStatus;
import com.kynsof.store.infrastructure.enumDto.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateOrderCommandHandler implements ICommandHandler<CreateOrderCommand> {

    private final IOrderService orderService;
    private final IProductService productService;
    private final ICustomerService customerService;

    public CreateOrderCommandHandler(IOrderService orderService, IProductService productService, ICustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }


    @Override
    public void handle(CreateOrderCommand command) {
        CustomerDto customerDto = customerService.findById(command.getCustomerId());
        List<OrderDetailDto> orderDetails = command.getOrderDetails().stream()
                .map(detail -> {
                    ProductEntityDto productDto = productService.findById(detail.getProductId());
                    return new OrderDetailDto(
                            UUID.randomUUID(),
                            detail.getProductId(),
                            productDto,
                            detail.getQuantity(),
                            detail.getPrice(),
                            OrderDetailStatus.PENDING
                    );
                }).collect(Collectors.toList());

        UUID id = orderService.create(new OrderEntityDto(
                UUID.randomUUID(),
                LocalDateTime.now(),
                OrderStatus.PENDING,
                orderDetails,
                command.getCustomerId(),
                customerDto
        ));
        command.setId(id);
    }
}
