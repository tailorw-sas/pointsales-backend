package com.kynsof.treatments.application.query.businessProcedure.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IBusinessProcedureService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchBusinessProcedureQueryHandler implements IQueryHandler<GetSearchBusinessProcedureQuery, PaginatedResponse>{
    private final IBusinessProcedureService service;
    
    public GetSearchBusinessProcedureQueryHandler(IBusinessProcedureService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchBusinessProcedureQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
