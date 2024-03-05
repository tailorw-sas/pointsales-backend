package com.kynsof.store.domain.dto;

import com.kynsof.store.infrastructure.enumDto.OrderStatus;
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
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderDetailDto> orderDetails;
    private UUID customerId;
    private CustomerDto customerDto;
}

