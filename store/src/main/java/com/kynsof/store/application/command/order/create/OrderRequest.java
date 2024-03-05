package com.kynsof.store.application.command.order.create;

import com.kynsof.store.application.command.order.OrderDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private UUID customerId;
    private List<OrderDetailRequest> orderDetails;
}

