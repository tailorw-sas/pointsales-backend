package com.kynsof.shift.application.query.service.getbyid;

import com.kynsof.shift.application.query.service.ServicesResponse;
import com.kynsof.shift.domain.dto.ServiceDto;
import com.kynsof.shift.domain.service.IServiceService;
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
        ServiceDto response = service.findByIds(query.getId());

        return new ServicesResponse(response);
    }
}
