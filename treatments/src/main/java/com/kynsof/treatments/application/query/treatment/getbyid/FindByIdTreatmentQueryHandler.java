package com.kynsof.treatments.application.query.treatment.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdTreatmentQueryHandler implements IQueryHandler<FindByIdTreatmentQuery, TreatmentResponse> {

    private final ITreatmentService serviceImpl;

    public FindByIdTreatmentQueryHandler(ITreatmentService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public TreatmentResponse handle(FindByIdTreatmentQuery query) {
        TreatmentDto treatmentDto = serviceImpl.findById(query.getId());

        return new TreatmentResponse(treatmentDto);
    }
}
