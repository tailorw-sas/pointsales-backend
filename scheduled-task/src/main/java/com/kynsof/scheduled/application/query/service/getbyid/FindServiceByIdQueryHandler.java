package com.kynsof.scheduled.application.query.service.getbyid;

import com.kynsof.scheduled.application.query.ServicesResponse;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ServiceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class FindServiceByIdQueryHandler implements IQueryHandler<FindServiceByIdQuery, ServicesResponse>  {

    private final ServiceServiceImpl serviceImpl;

    public FindServiceByIdQueryHandler(ServiceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ServicesResponse handle(FindServiceByIdQuery query) {
        ServiceDto response = serviceImpl.findById(query.getId());

        return new ServicesResponse(response);
    }
}
