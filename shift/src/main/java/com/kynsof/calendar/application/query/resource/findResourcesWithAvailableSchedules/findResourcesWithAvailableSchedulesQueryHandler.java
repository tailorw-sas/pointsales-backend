package com.kynsof.calendar.application.query.resource.findResourcesWithAvailableSchedules;

import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class findResourcesWithAvailableSchedulesQueryHandler implements IQueryHandler<findResourcesWithAvailableSchedulesQuery, PaginatedResponse>  {

    private final IResourceService service;

    public findResourcesWithAvailableSchedulesQueryHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(findResourcesWithAvailableSchedulesQuery query) {

        return service.findResourcesWithAvailableSchedules(query.getBusinessId(),query.getServiceId(),
                query.getDate(), query.getPageable());
    }
}
