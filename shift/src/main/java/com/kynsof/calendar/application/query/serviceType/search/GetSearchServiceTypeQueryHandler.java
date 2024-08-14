package com.kynsof.calendar.application.query.serviceType.search;

import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchServiceTypeQueryHandler implements IQueryHandler<GetSearchServiceTypeQuery, PaginatedResponse>{
    private final IServiceTypeService service;
    
    public GetSearchServiceTypeQueryHandler(IServiceTypeService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchServiceTypeQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
