package com.kynsof.scheduled.application.query.service.getbyid;

import com.kynsof.scheduled.application.query.ServicesResponse;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindServiceByIdQueryHandler implements IQueryHandler<FindServiceByIdQuery, ServicesResponse>  {

    private final IServiceService service;

    public FindServiceByIdQueryHandler(IServiceService service) {
        this.service = service;
    }

    @Override
    public ServicesResponse handle(FindServiceByIdQuery query) {
        ServiceDto response = service.findById(query.getId());

        return new ServicesResponse(response);
    }
}
