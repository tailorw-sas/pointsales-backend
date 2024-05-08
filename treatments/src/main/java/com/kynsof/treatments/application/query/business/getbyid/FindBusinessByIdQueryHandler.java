package com.kynsof.treatments.application.query.business.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.business.search.BusinessResponse;
import com.kynsof.treatments.domain.dto.BusinessDto;
import com.kynsof.treatments.domain.service.IBusinessService;
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
