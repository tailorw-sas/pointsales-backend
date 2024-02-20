package com.kynsof.calendar.application.query.business.search;

import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchBusinessQueryHandler implements IQueryHandler<GetSearchBusinessQuery, PaginatedResponse>{
    private final IBusinessService service;
    
    public GetSearchBusinessQueryHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchBusinessQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
