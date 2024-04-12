package com.kynsof.treatments.application.query.vaccine.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IPatientVaccineService;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchVaccineQueryHandler implements IQueryHandler<GetSearchVaccineQuery, PaginatedResponse>{

    private final IVaccineService serviceImpl;

    public GetSearchVaccineQueryHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchVaccineQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
