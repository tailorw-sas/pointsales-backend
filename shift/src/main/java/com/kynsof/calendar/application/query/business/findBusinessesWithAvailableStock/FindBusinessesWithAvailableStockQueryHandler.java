package com.kynsof.calendar.application.query.business.findBusinessesWithAvailableStock;

import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class FindBusinessesWithAvailableStockQueryHandler implements IQueryHandler<FindBusinessesWithAvailableStockQuery, PaginatedResponse>{
    private final IBusinessService service;
    
    public FindBusinessesWithAvailableStockQueryHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindBusinessesWithAvailableStockQuery query) {
        return this.service.findBusinessesWithAvailableStockByDateAndService(query.getDate(), query.getServiceId(), query.getPageable());
    }
}
