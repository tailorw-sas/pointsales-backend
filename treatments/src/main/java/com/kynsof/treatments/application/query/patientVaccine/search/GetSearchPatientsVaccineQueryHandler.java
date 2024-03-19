package com.kynsof.treatments.application.query.patientVaccine.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IPatientVaccineService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchPatientsVaccineQueryHandler implements IQueryHandler<GetSearchPatientsVaccineQuery, PaginatedResponse>{

    private final IPatientVaccineService serviceImpl;

    public GetSearchPatientsVaccineQueryHandler(IPatientVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchPatientsVaccineQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
