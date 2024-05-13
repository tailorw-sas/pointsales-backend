package com.kynsof.calendar.application.query.businessService.getbyid;

import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindBusinessServicesByIdQueryHandler implements IQueryHandler<FindBusinessServiceByIdQuery, BusinessServicesResponse>  {

    private final IBusinessServicesService service;

    public FindBusinessServicesByIdQueryHandler(IBusinessServicesService service) {
        this.service = service;
    }

    @Override
    public BusinessServicesResponse handle(FindBusinessServiceByIdQuery query) {
        BusinessServicesDto response = service.findById(query.getId());

        return new BusinessServicesResponse(response);
    }
}
