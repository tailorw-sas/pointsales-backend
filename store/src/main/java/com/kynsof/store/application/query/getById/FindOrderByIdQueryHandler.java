package com.kynsof.store.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.domain.services.IOrderService;
import com.kynsof.store.application.query.getAll.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class FindOrderByIdQueryHandler implements IQueryHandler<FindOrderByIdQuery, OrderResponse> {

    private  IOrderService orderService;

//    @Autowired
//    public FindOrderByIdQueryHandler(IOrderService orderService) {
//        this.orderService = orderService;
//    }

    @Override
    public OrderResponse handle(FindOrderByIdQuery query) {
//        OrderDto order = orderService.findById(query.getId());
//        // La conversión a OrderResponse depende de los detalles de la implementación de OrderDto
//        return new OrderResponse(order);
        return null;
    }
}
