package com.kynsoft.rrhh.application.query.doctor.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchDoctorQueryHandler implements IQueryHandler<GetSearchDoctorQuery, PaginatedResponse>{
    private final IDoctorService service;

    public GetSearchDoctorQueryHandler(IDoctorService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchDoctorQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
