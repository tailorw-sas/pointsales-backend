package com.kynsof.treatments.application.query.patientVaccine.getall;

import com.kynsof.treatments.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IPatientVaccineService;
import org.springframework.stereotype.Component;

@Component
public class GetAllPatientVaccineQueryHandler implements IQueryHandler<GetAllPatientVaccineQuery, PaginatedResponse> {

    private final IPatientVaccineService serviceImpl;

    public GetAllPatientVaccineQueryHandler(IPatientVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllPatientVaccineQuery query) {

        return this.serviceImpl.findAll(query.getPageable(),  query.getPatientId());
    }
}
