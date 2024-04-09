package com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class GetAvailableDatesAndSlotsQuery implements IQuery {

    private final UUID resourceId;
    private final UUID businessId;
    private final LocalDate startDate;
    private final LocalDate finalDate;

    public GetAvailableDatesAndSlotsQuery(UUID resourceId, UUID businessId, LocalDate startDate, LocalDate finalDate) {

        this.resourceId = resourceId;
        this.businessId = businessId;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

}
