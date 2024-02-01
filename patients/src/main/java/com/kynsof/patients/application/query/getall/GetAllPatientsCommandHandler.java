package com.kynsof.patients.application.query.getall;

import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GetAllPatientsCommandHandler implements IQueryHandler<FindPatientsWithFilterQuery, PaginatedResponse>{

    private final PatientsServiceImpl serviceImpl;

    public GetAllPatientsCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(FindPatientsWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdPatients(), query.getIdentification());
    }
}
