package com.kynsof.calendar.application.query.schedule.getbyid;

import com.kynsof.calendar.application.query.ScheduleResponse;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindScheduleByIdQueryHandler implements IQueryHandler<FindScheduleByIdQuery, ScheduleResponse>  {

    private final IScheduleService service;

    public FindScheduleByIdQueryHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public ScheduleResponse handle(FindScheduleByIdQuery query) {
        ScheduleDto response = service.findById(query.getId());

        return new ScheduleResponse(response);
    }
}
