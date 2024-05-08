package com.kynsof.treatments.application.query.business.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IBusinessService;
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
