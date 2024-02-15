package com.kynsof.scheduled.application.query.schedule.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.service.IScheduleService;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
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
