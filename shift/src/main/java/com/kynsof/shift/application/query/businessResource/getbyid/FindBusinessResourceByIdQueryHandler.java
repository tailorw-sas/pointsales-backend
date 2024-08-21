package com.kynsof.shift.application.query.businessResource.getbyid;

import com.kynsof.shift.domain.dto.BusinessResourceDto;
import com.kynsof.shift.domain.service.IBusinessResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindBusinessResourceByIdQueryHandler implements IQueryHandler<FindBusinessResourceByIdQuery, BusinessResourceResponse>  {

    private final IBusinessResourceService service;

    public FindBusinessResourceByIdQueryHandler(IBusinessResourceService service) {
        this.service = service;
    }

    @Override
    public BusinessResourceResponse handle(FindBusinessResourceByIdQuery query) {
        BusinessResourceDto response = service.findById(query.getId());

        return new BusinessResourceResponse(response);
    }
}
