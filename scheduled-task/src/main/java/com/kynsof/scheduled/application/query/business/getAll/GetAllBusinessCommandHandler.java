package com.kynsof.scheduled.application.query.business.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.service.IBusinessService;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GetAllBusinessCommandHandler implements IQueryHandler<FindBusinessWithFilterQuery, PaginatedResponse>{

    private final IBusinessService service;

    public GetAllBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindBusinessWithFilterQuery query) {

        return this.service.findAll(query.getPageable(), query.getIdObject(), query.getFilter());
    }
}
