package com.kynsof.calendar.application.query.schedule.getAvailabilityByRangeDate;

import com.kynsof.calendar.domain.dto.ReservationRequestDto;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetAvailabilityByRangeDateQueryHandler implements IQueryHandler<GetAvailabilityByRangeDateQuery, PaginatedResponse> {
    private final IScheduleService service;

    public GetAvailabilityByRangeDateQueryHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetAvailabilityByRangeDateQuery query) {

        return this.service.getResourcesWithContinuousAvailability(
                new ReservationRequestDto(query.getStartDate(), query.getEndDate(), query.getServiceId(), query.getBusinessId()),
                query.getPageable());
    }
}
