package com.kynsof.store.application.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDetailRequest {
    private UUID productId;
    private Integer quantity;
    private Double price;
    private UUID orderId;
}
