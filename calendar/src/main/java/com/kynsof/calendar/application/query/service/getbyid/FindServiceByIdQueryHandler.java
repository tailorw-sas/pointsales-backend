package com.kynsof.calendar.application.query.service.getbyid;

import com.kynsof.calendar.application.query.ServicesResponse;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IServiceService;
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
