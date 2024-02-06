package com.kynsof.scheduled.application.query.schedule.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GetAllScheduleCommandHandler implements IQueryHandler<FindScheduleWithFilterQuery, PaginatedResponse>{

    private final ScheduleServiceImpl serviceImpl;

    public GetAllScheduleCommandHandler(ScheduleServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(FindScheduleWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdResource(), query.getDate(), query.getStatus(), query.getStartDate(), query.getEndDate());
    }
}
