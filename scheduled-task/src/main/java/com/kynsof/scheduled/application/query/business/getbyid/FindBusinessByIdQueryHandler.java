package com.kynsof.scheduled.application.query.business.getbyid;

import com.kynsof.scheduled.application.query.BusinessResponse;
import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class FindBusinessByIdQueryHandler implements IQueryHandler<FindBusinessByIdQuery, BusinessResponse>  {

    private final BusinessServiceImpl serviceImpl;

    public FindBusinessByIdQueryHandler(BusinessServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public BusinessResponse handle(FindBusinessByIdQuery query) {
        BusinessDto response = serviceImpl.findById(query.getId());

        return new BusinessResponse(response);
    }
}
