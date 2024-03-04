package com.kynsof.store.application.query.order.findById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.domain.dto.OrderEntityDto;
import com.kynsof.store.domain.services.IOrderService;
import org.springframework.stereotype.Component;

@Component
public class FindOrderByIdQueryHandler implements IQueryHandler<FindOrderByIdQuery, OrderFindByIdResponse> {

    private final  IOrderService orderService;

    public FindOrderByIdQueryHandler(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderFindByIdResponse handle(FindOrderByIdQuery query) {
        OrderEntityDto order = orderService.findById(query.getId());
        return new OrderFindByIdResponse(order);
    }
}
