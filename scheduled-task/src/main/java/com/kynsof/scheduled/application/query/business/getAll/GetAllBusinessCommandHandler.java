package com.kynsof.scheduled.application.query.business.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GetAllBusinessCommandHandler implements IQueryHandler<FindBusinessWithFilterQuery, PaginatedResponse>{

    private final BusinessServiceImpl serviceImpl;

    public GetAllBusinessCommandHandler(BusinessServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(FindBusinessWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdObject(), query.getFilter());
    }
}
