package com.kynsof.patients.application.query.allergy.getall;

import com.kynsof.patients.domain.service.IAllergyService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetAllAllergyQueryHandler implements IQueryHandler<GetAllAllergyQuery, PaginatedResponse>{

    private final IAllergyService serviceImpl;

    public GetAllAllergyQueryHandler(IAllergyService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllAllergyQuery query) {

        return this.serviceImpl.findAll(query.getPageable());
    }
}
