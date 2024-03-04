package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.OrderDetailDto;
import com.kynsof.store.domain.dto.OrderEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponse implements IResponse {
    private UUID id;
    private UUID supplierId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderDetailResponse> orderDetails;

    public OrderResponse(OrderEntityDto orderDto) {
        this.id = orderDto.getId();
        this.supplierId = orderDto.getSupplierId();
        this.orderDate = orderDto.getOrderDate();
        this.status = orderDto.getStatus();
        this.orderDetails = orderDto.getOrderDetails().stream()
                .map(OrderDetailResponse::new)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class OrderDetailResponse {
        private UUID productId;
        private Integer quantity;
        private Double price;

        public OrderDetailResponse(OrderDetailDto orderDetailDto) {
            this.productId = orderDetailDto.getProductId();
            this.quantity = orderDetailDto.getQuantity();
            this.price = orderDetailDto.getPrice();
        }
    }
}

