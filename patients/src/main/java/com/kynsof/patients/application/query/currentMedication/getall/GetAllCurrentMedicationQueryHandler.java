package com.kynsof.patients.application.query.currentMedication.getall;

import com.kynsof.patients.domain.service.ICurrentMedicationService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetAllCurrentMedicationQueryHandler implements IQueryHandler<GetAllCurrentMedicationQuery, PaginatedResponse>{

    private final ICurrentMedicationService serviceImpl;

    public GetAllCurrentMedicationQueryHandler(ICurrentMedicationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllCurrentMedicationQuery query) {

        return this.serviceImpl.findAll(query.getPageable());
    }
}
