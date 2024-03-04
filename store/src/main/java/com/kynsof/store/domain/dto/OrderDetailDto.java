package com.kynsof.store.domain.dto;

import com.kynsof.store.infrastructure.enumDto.OrderDetailStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailDto {
    private UUID id;
    private UUID productId;
    private ProductEntityDto product;
    private Integer quantity;
    private Double price;
    private OrderDetailStatus status;
}
