package com.kynsof.rrhh.application.query.doctor.search;

import com.kynsof.rrhh.domain.interfaces.services.IUserSystemService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchDoctorQueryHandler implements IQueryHandler<GetSearchDoctorQuery, PaginatedResponse>{
    private final IUserSystemService service;

    public GetSearchDoctorQueryHandler(IUserSystemService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchDoctorQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
