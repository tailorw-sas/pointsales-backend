package com.kynsof.treatments.application.query.paymentAllergy.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IPatientAllergyService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchPaymentAllergyQueryHandler implements IQueryHandler<GetSearchPaymentAllergyQuery, PaginatedResponse>{

    private final IPatientAllergyService serviceImpl;

    public GetSearchPaymentAllergyQueryHandler(IPatientAllergyService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchPaymentAllergyQuery query) {
        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
