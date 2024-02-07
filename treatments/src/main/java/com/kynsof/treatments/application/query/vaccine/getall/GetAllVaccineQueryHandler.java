package com.kynsof.treatments.application.query.vaccine.getall;

import com.kynsof.treatments.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

@Component
public class GetAllVaccineQueryHandler implements IQueryHandler<GetAllVaccineQuery, PaginatedResponse> {

    private final IVaccineService serviceImpl;

    public GetAllVaccineQueryHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllVaccineQuery query) {

        return this.serviceImpl.findAll(query.getPageable(),  query.getName(), query.getDescription());
    }
}
