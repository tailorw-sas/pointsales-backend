package com.kynsof.calendar.application.query.resource.getresourcesbyidservice;

import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetResourcesByIdServiceQueryHandler implements IQueryHandler<FindResourcesByIdServiceQuery, PaginatedResponse>{
    private final IResourceService service;
    
    public GetResourcesByIdServiceQueryHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindResourcesByIdServiceQuery query) {

        return this.service.findResourcesByServiceId(query.getId(), query.getPageable());
    }
}
