package com.kynsof.calendar.application.query.schedule.getAll;

import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetAllScheduleCommandHandler implements IQueryHandler<FindScheduleWithFilterQuery, PaginatedResponse>{

    private final IScheduleService service;

    public GetAllScheduleCommandHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindScheduleWithFilterQuery query) {

        return this.service.findAll(query.getPageable(), query.getIdResource(), query.getDate(), query.getStatus(), query.getStartDate(), query.getEndDate());
    }
}
