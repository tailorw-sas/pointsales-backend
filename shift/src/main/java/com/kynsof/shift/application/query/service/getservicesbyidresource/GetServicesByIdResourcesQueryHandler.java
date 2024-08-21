package com.kynsof.shift.application.query.service.getservicesbyidresource;

import com.kynsof.shift.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetServicesByIdResourcesQueryHandler implements IQueryHandler<FindServicesByIdResourceQuery, PaginatedResponse>{
    private final IServiceService service;
    
    public GetServicesByIdResourcesQueryHandler(IServiceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindServicesByIdResourceQuery query) {

        return this.service.findServicesByResourceId(query.getPageable(), query.getId());
    }
}
