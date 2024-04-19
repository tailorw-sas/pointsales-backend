package com.kynsof.treatments.application.query.procedure.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IProcedureService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchProcedureQueryHandler implements IQueryHandler<GetSearchProcedureQuery, PaginatedResponse>{

    private final IProcedureService serviceImpl;

    public GetSearchProcedureQueryHandler(IProcedureService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchProcedureQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
