package com.kynsof.treatments.application.query.treatment.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchTreatmentQueryHandler implements IQueryHandler<GetSearchTreatmentQuery, PaginatedResponse>{

    private final ITreatmentService serviceImpl;

    public GetSearchTreatmentQueryHandler(ITreatmentService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchTreatmentQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
