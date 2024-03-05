package com.kynsof.store.application.query.order.findById;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.OrderDetailDto;
import com.kynsof.store.domain.dto.OrderEntityDto;
import com.kynsof.store.infrastructure.enumDto.OrderDetailStatus;
import com.kynsof.store.infrastructure.enumDto.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderFindByIdResponse implements IResponse {
    private UUID id;
    private UUID supplierId;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderDetailResponse> orderDetails;
    private Double total;
    public OrderFindByIdResponse(OrderEntityDto orderDto) {
        this.id = orderDto.getId();
        this.orderDate = orderDto.getOrderDate();
        this.status = orderDto.getStatus();
        this.orderDetails = orderDto.getOrderDetails().stream()
                .map(OrderDetailResponse::new)
                .collect(Collectors.toList());
        this.total = orderDetails.stream()
                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
                .sum();
    }

    @Getter
    @Setter
    public static class OrderDetailResponse {
        private UUID productId;
        private Integer quantity;
        private Double price;
        private OrderDetailStatus status;


        public OrderDetailResponse(OrderDetailDto orderDetailDto) {
            this.productId = orderDetailDto.getProductId();
            this.quantity = orderDetailDto.getQuantity();
            this.price = orderDetailDto.getPrice();
            this.status = orderDetailDto.getStatus();
        }
    }
}

