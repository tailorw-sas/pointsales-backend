package com.kynsof.store.application.query.order;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.OrderEntityDto;
import com.kynsof.store.infrastructure.enumDto.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderResponse implements IResponse {
    private UUID id;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Double total;
    public OrderResponse(OrderEntityDto orderDto) {
        this.id = orderDto.getId();
        this.orderDate = orderDto.getOrderDate();
        this.status = orderDto.getStatus();
        this.total = orderDto.getOrderDetails().stream()
                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
                .sum();
    }
}

