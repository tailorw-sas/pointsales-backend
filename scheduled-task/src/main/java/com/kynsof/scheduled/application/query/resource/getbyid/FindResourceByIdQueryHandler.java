package com.kynsof.scheduled.application.query.resource.getbyid;

import com.kynsof.scheduled.application.query.ResourceResponse;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class FindResourceByIdQueryHandler implements IQueryHandler<FindResourceByIdQuery, ResourceResponse>  {

    private final ResocurceServiceImpl serviceImpl;

    public FindResourceByIdQueryHandler(ResocurceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ResourceResponse handle(FindResourceByIdQuery query) {
        ResourceDto response = serviceImpl.findById(query.getId());

        return new ResourceResponse(response);
    }
}
