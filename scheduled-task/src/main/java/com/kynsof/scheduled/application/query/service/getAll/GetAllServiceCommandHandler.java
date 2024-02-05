package com.kynsof.scheduled.application.query.service.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ServiceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GetAllServiceCommandHandler implements IQueryHandler<FindServiceWithFilterQuery, PaginatedResponse>{

    private final ServiceServiceImpl serviceImpl;

    public GetAllServiceCommandHandler(ServiceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(FindServiceWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdObject(), query.getFilter());
    }
}
