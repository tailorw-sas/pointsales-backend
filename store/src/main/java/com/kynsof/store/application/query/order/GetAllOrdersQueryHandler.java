package com.kynsof.store.application.query.order;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.IOrderService;
import org.springframework.stereotype.Component;

@Component
public class GetAllOrdersQueryHandler implements IQueryHandler<GetAllOrdersQuery, PaginatedResponse> {

    private  IOrderService orderService;

//    @Autowired
//    public GetAllOrdersQueryHandler(IOrderService orderService) {
//        this.orderService = orderService;
//    }

    @Override
    public PaginatedResponse handle(GetAllOrdersQuery query) {
//        Page<OrderDto> orders = orderService.findAll(query.getPageable());
//        Page<OrderResponse> orderResponses = orders.map(OrderResponse::new);
//        return new PaginatedResponse<>(orderResponses);
        return null;
    }
}

