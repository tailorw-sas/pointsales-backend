package com.kynsof.shift.application.query.business.findDetailedAvailableSchedulesByResourceAndBusinessAndDateRange;

import com.kynsof.shift.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class findDetailedAvailableSchedulesByResourceAndBusinessAndDateRangeQueryHandler implements IQueryHandler<findDetailedAvailableSchedulesByResourceAndBusinessAndDateRangeQuery, PaginatedResponse>{
    private final IBusinessService service;
    
    public findDetailedAvailableSchedulesByResourceAndBusinessAndDateRangeQueryHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(findDetailedAvailableSchedulesByResourceAndBusinessAndDateRangeQuery query) {
        return this.service.findDetailedAvailableSchedulesByResourceAndBusinessAndDateRange(query.getStartDate(),
                query.getEndDate(), query.getServiceId(),query.getBusinessName(), query.getPageable());
    }
}
