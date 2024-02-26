package com.kynsof.calendar.application.query.schedule.search;

import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchScheduleQueryHandler implements IQueryHandler<GetSearchScheduleQuery, PaginatedResponse>{
    private final IScheduleService service;
    
    public GetSearchScheduleQueryHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchScheduleQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
