package com.kynsof.calendar.application.query.serviceType.getbyid;

import com.kynsof.calendar.application.query.ServiceTypeResponse;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindServiceTypeByIdQueryHandler implements IQueryHandler<FindServiceTypeByIdQuery, ServiceTypeResponse>  {

    private final IServiceTypeService service;

    public FindServiceTypeByIdQueryHandler(IServiceTypeService service) {
        this.service = service;
    }

    @Override
    public ServiceTypeResponse handle(FindServiceTypeByIdQuery query) {
        ServiceTypeDto response = service.findById(query.getId());

        return new ServiceTypeResponse(response);
    }
}
