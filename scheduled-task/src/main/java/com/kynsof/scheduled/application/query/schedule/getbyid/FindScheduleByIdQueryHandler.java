package com.kynsof.scheduled.application.query.schedule.getbyid;

import com.kynsof.scheduled.application.query.ScheduleResponse;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class FindScheduleByIdQueryHandler implements IQueryHandler<FindScheduleByIdQuery, ScheduleResponse>  {

    private final ScheduleServiceImpl serviceImpl;

    public FindScheduleByIdQueryHandler(ScheduleServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ScheduleResponse handle(FindScheduleByIdQuery query) {
        ScheduleDto response = serviceImpl.findById(query.getId());

        return new ScheduleResponse(response);
    }
}
