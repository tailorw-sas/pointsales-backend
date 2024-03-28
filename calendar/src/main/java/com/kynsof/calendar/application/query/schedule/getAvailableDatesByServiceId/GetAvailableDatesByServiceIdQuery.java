package com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class GetAvailableDatesByServiceIdQuery implements IQuery {

    private final UUID serviceId;
    private  final LocalDate starDate;
    private  final LocalDate finalDate;

    public GetAvailableDatesByServiceIdQuery(UUID serviceId, LocalDate starDate, LocalDate finalDate) {
        this.serviceId = serviceId;
        this.starDate = starDate;
        this.finalDate = finalDate;
    }

}
