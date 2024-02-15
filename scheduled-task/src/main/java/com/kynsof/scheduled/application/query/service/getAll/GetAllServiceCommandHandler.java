package com.kynsof.scheduled.application.query.service.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.service.IServiceService;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GetAllServiceCommandHandler implements IQueryHandler<FindServiceWithFilterQuery, PaginatedResponse>{

    private final IServiceService service;

    public GetAllServiceCommandHandler(IServiceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindServiceWithFilterQuery query) {

        return this.service.findAll(query.getPageable(), query.getIdObject(), query.getFilter());
    }
}
