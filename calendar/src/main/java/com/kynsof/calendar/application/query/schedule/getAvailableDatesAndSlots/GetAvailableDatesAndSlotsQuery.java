package com.kynsof.calendar.application.query.schedule.getAvailableDatesAndSlots;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GetAvailableDatesAndSlotsQuery implements IQuery {

    private final UUID resourceId;
    private final UUID businessId;

    public GetAvailableDatesAndSlotsQuery(UUID resourceId, UUID businessId) {

        this.resourceId = resourceId;
        this.businessId = businessId;
    }

}
