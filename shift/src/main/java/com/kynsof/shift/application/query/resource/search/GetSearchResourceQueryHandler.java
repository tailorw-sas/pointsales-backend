package com.kynsof.shift.application.query.resource.search;

import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchResourceQueryHandler implements IQueryHandler<GetSearchResourceQuery, PaginatedResponse>{
    private final IResourceService service;
    
    public GetSearchResourceQueryHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchResourceQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
