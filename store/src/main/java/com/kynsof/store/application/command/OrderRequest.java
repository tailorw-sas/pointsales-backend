package com.kynsof.store.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private UUID supplierId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderDetailRequest> orderDetails;
}

