package com.kynsof.calendar.application.query.schedule.getAvailableDatesByServiceId;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GetAvailableDatesByServiceIdQuery implements IQuery {

    private final UUID serviceId;

    public GetAvailableDatesByServiceIdQuery(UUID serviceId) {
        this.serviceId = serviceId;
    }

}
