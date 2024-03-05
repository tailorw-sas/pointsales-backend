package com.kynsof.store.application.query.order.getAll;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.IOrderService;
import org.springframework.stereotype.Component;

@Component
public class GetAllOrdersQueryHandler implements IQueryHandler<GetAllOrdersQuery, PaginatedResponse> {

    private final IOrderService orderService;


    public GetAllOrdersQueryHandler(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public PaginatedResponse handle(GetAllOrdersQuery query) {
        return orderService.search(query.getPageable(),query.getFilter());
    }
}

