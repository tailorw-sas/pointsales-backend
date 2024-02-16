package com.kynsof.calendar.application.query.business.getAll;

import com.kynsof.calendar.application.PaginatedResponse;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
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
