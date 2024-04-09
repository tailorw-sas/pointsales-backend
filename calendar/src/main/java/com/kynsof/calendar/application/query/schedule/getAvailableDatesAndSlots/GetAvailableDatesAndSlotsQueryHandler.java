package com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots;

import com.kynsof.calendar.domain.dto.AvailableDateDto;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAvailableDatesAndSlotsQueryHandler implements IQueryHandler<GetAvailableDatesAndSlotsQuery, GetAvailableDatesAndSlotsResponse>  {

    private final IScheduleService service;

    public GetAvailableDatesAndSlotsQueryHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public GetAvailableDatesAndSlotsResponse handle(GetAvailableDatesAndSlotsQuery query) {
        List<AvailableDateDto> response = service.getAvailableDatesAndSlots(query.getResourceId(),query.getBusinessId(), query.getStartDate(),
                query.getFinalDate());

        return new GetAvailableDatesAndSlotsResponse(response);
    }
}
