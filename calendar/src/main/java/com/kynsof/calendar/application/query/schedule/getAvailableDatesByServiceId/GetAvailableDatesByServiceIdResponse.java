package com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId;

import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class GetAvailableDatesByServiceIdResponse implements IResponse {
    private List<LocalDate> localDates;
}
