package com.kynsof.calendar.application.query.businessResource.search;

import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchBusinessResourceQueryHandler implements IQueryHandler<GetSearchBusinessResourceQuery, PaginatedResponse>{
    private final IBusinessResourceService service;
    
    public GetSearchBusinessResourceQueryHandler(IBusinessResourceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchBusinessResourceQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
