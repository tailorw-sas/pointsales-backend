package com.kynsof.calendar.application.query.service.getAll;

import com.kynsof.calendar.application.PaginatedResponse;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
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
