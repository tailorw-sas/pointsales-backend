package com.kynsof.shift.application.query.business.getbyid;

import com.kynsof.shift.application.query.BusinessResponse;
import com.kynsof.shift.domain.dto.BusinessDto;
import com.kynsof.shift.domain.service.IBusinessService;
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
