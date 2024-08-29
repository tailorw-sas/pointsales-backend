package com.kynsof.calendar.application.query.schedule.getUniqueAvailableServices;

import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetUniqueAvailableServicesQueryHandler implements IQueryHandler<GetUniqueAvailableServicesQuery, PaginatedResponse>  {

    private final IScheduleService service;

    public GetUniqueAvailableServicesQueryHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetUniqueAvailableServicesQuery query) {

        return service.getUniqueAvailableServices(query.getPageable());
    }
}
