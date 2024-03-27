package com.kynsof.calendar.application.query.resource.findResourcesWithAvailableSchedules;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class findResourcesWithAvailableSchedulesQuery implements IQuery {

    private final Pageable pageable;
    private final LocalDate date;
    private final UUID serviceId;
    private final UUID businessId;

    public findResourcesWithAvailableSchedulesQuery(Pageable pageable, LocalDate date, UUID serviceId, UUID businessId) {
        this.pageable = pageable;
        this.date = date;
        this.serviceId = serviceId;
        this.businessId = businessId;

    }
}
