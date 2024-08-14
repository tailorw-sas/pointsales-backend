package com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId;

import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GetAvailableDatesByServiceIdQueryHandler implements IQueryHandler<GetAvailableDatesByServiceIdQuery, GetAvailableDatesByServiceIdResponse>  {

    private final IScheduleService service;

    public GetAvailableDatesByServiceIdQueryHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public GetAvailableDatesByServiceIdResponse handle(GetAvailableDatesByServiceIdQuery query) {
        List<LocalDate> response = service.findDistinctAvailableDatesByServiceIdAndDateRange(query.getServiceId(), query.getStarDate(),
                query.getFinalDate());

        return new GetAvailableDatesByServiceIdResponse(response);
    }
}
