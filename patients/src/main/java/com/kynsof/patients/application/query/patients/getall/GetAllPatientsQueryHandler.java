package com.kynsof.patients.application.query.patients.getall;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

@Component
public class GetAllPatientsQueryHandler implements IQueryHandler<GetAllPatientsFilterQuery, PaginatedResponse>{

    private final IPatientsService serviceImpl;

    public GetAllPatientsQueryHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllPatientsFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable());
    }
}
