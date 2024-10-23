package com.kynsof.treatments.application.query.serviceType.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IServiceTypeService;
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
