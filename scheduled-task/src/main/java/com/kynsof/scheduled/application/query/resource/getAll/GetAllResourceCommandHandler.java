package com.kynsof.scheduled.application.query.resource.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
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
