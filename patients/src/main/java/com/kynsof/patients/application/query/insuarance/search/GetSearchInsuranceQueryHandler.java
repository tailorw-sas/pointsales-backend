package com.kynsof.patients.application.query.insuarance.search;

import com.kynsof.patients.domain.service.IInsuranceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchInsuranceQueryHandler implements IQueryHandler<GetSearchInsuranceQuery, PaginatedResponse>{

    private final IInsuranceService serviceImpl;

    public GetSearchInsuranceQueryHandler(IInsuranceService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchInsuranceQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
