package com.kynsof.store.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrderEntityDto {
    private UUID id;
    private UUID supplierId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderDetailDto> orderDetails;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderDetailDto {
        private UUID productId;
        private Integer quantity;
        private Double price;
    }
}

