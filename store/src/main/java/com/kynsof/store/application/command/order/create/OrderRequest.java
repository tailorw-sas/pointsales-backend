package com.kynsof.store.application.command.order.create;

import com.kynsof.store.application.command.OrderDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderDetailRequest> orderDetails;
}

