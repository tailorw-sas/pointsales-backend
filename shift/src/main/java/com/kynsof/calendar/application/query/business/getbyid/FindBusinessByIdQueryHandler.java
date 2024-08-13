package com.kynsof.calendar.application.query.business.getbyid;

import com.kynsof.calendar.application.query.BusinessResponse;
import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindBusinessByIdQueryHandler implements IQueryHandler<FindBusinessByIdQuery, BusinessResponse>  {

    private final IBusinessService service;

    public FindBusinessByIdQueryHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public BusinessResponse handle(FindBusinessByIdQuery query) {
        BusinessDto response = service.findById(query.getId());

        return new BusinessResponse(response);
    }
}
