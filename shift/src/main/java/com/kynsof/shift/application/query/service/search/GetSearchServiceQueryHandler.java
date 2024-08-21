package com.kynsof.shift.application.query.service.search;

import com.kynsof.shift.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchServiceQueryHandler implements IQueryHandler<GetSearchServiceQuery, PaginatedResponse>{
    private final IServiceService service;
    
    public GetSearchServiceQueryHandler(IServiceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchServiceQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
