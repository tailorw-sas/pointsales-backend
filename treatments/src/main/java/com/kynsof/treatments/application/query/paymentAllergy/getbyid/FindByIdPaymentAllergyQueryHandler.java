package com.kynsof.treatments.application.query.paymentAllergy.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.PathologicalHistoryDto;
import com.kynsof.treatments.domain.service.IPatientAllergyService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdPaymentAllergyQueryHandler implements IQueryHandler<FindByIdPatientAllergyQuery, PaymentAllergyResponse> {

    private final IPatientAllergyService serviceImpl;

    public FindByIdPaymentAllergyQueryHandler(IPatientAllergyService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaymentAllergyResponse handle(FindByIdPatientAllergyQuery query) {
        PathologicalHistoryDto object = serviceImpl.findById(query.getId());

        return new PaymentAllergyResponse(object);
    }
}
