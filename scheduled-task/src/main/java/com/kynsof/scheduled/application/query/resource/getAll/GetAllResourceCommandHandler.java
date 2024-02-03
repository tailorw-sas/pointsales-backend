package com.kynsof.scheduled.application.query.resource.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GetAllResourceCommandHandler implements IQueryHandler<FindResourceWithFilterQuery, PaginatedResponse>{

    private final ResocurceServiceImpl serviceImpl;

    public GetAllResourceCommandHandler(ResocurceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(FindResourceWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdQualification(), query.getFilter());
    }
}
