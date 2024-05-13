package com.kynsof.calendar.application.query.businessService.getservicesbyresources;

import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetServicesByIdResourceQueryHandler implements IQueryHandler<FindServiceByIdResourcesQuery, PaginatedResponse>{
    private final IBusinessServicesService service;
    
    public GetServicesByIdResourceQueryHandler(IBusinessServicesService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindServiceByIdResourcesQuery query) {

        return this.service.findServicesByResourceId(query.getPageable(), query.getId());
    }
}
