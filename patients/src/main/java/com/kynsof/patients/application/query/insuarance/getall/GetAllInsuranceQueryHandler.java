package com.kynsof.patients.application.query.insuarance.getall;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.service.IInsuranceService;
import org.springframework.stereotype.Component;

@Component
public class GetAllInsuranceQueryHandler implements IQueryHandler<GetAllInsuranceQuery, PaginatedResponse>{

    private final IInsuranceService serviceImpl;

    public GetAllInsuranceQueryHandler(IInsuranceService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllInsuranceQuery query) {

        return this.serviceImpl.findAll(query.getPageable());
    }
}
