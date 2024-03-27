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
        List<LocalDate> response = service.getAvailableDatesByServiceId(query.getServiceId());

        return new GetAvailableDatesByServiceIdResponse(response);
    }
}
