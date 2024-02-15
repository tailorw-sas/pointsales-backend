package com.kynsof.scheduled.application.query.resource.getbyid;

import com.kynsof.scheduled.application.query.ResourceResponse;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindResourceByIdQueryHandler implements IQueryHandler<FindResourceByIdQuery, ResourceResponse>  {

    private final IResourceService service;

    public FindResourceByIdQueryHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public ResourceResponse handle(FindResourceByIdQuery query) {
        ResourceDto response = service.findById(query.getId());

        return new ResourceResponse(response);
    }
}
