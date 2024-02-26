package com.kynsof.calendar.application.query.resource.getAll;

import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetAllResourceCommandHandler implements IQueryHandler<FindResourceWithFilterQuery, PaginatedResponse>{

    private final IResourceService service;

    public GetAllResourceCommandHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindResourceWithFilterQuery query) {

        return this.service.findAll(query.getPageable(), query.getIdQualification(), query.getFilter());
    }
}
