package com.kynsof.shift.application.query.businessService.GetServicesSimpleByBusinessId;

import com.kynsof.shift.domain.service.IBusinessServicesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetServicesSimpleByIdBusinessQueryHandler implements IQueryHandler<FindServiceSimpleByIdBusinessQuery, PaginatedResponse>{
    private final IBusinessServicesService service;
    
    public GetServicesSimpleByIdBusinessQueryHandler(IBusinessServicesService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindServiceSimpleByIdBusinessQuery query) {

        return this.service.findServicesSimpleByBusinessId(query.getPageable(), query.getId());
    }
}
