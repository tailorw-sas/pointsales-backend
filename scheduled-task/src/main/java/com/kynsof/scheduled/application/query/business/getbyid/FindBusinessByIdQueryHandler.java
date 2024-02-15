package com.kynsof.scheduled.application.query.business.getbyid;

import com.kynsof.scheduled.application.query.BusinessResponse;
import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.service.IBusinessService;
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
