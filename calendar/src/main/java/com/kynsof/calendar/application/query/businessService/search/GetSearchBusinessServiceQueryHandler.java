package com.kynsof.calendar.application.query.businessService.search;

import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchBusinessServiceQueryHandler implements IQueryHandler<GetSearchBusinessServiceQuery, PaginatedResponse>{
    private final IBusinessServicesService service;
    
    public GetSearchBusinessServiceQueryHandler(IBusinessServicesService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchBusinessServiceQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
