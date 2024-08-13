package com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots;

import com.kynsof.calendar.domain.dto.AvailableDateDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetAvailableDatesAndSlotsResponse implements IResponse {
    private List<AvailableDateDto> schedules;
}
