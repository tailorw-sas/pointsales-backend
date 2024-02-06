package com.kynsof.treatments.application.query.cie10.getAll;

import com.kynsof.treatments.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.service.ICie10Service;
import org.springframework.stereotype.Component;

@Component
public class GetAllCie10QueryHandler implements IQueryHandler<GetAllCie10Query, PaginatedResponse>{

    private final ICie10Service serviceImpl;

    public GetAllCie10QueryHandler(ICie10Service serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllCie10Query query) {
        return this.serviceImpl.findAll(query.getPageable(), query.getName(), query.getCode());
    }
}
