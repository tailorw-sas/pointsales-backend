package com.kynsof.shift.application.query.businessService.getServicesByBusiness;

import com.kynsof.shift.domain.service.IBusinessServicesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetServicesByIdBusinessQueryHandler implements IQueryHandler<FindServiceByIdBusinessQuery, PaginatedResponse>{
    private final IBusinessServicesService service;
    
    public GetServicesByIdBusinessQueryHandler(IBusinessServicesService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindServiceByIdBusinessQuery query) {

        return this.service.findServicesByBusinessId(query.getPageable(), query.getId());
    }
}
