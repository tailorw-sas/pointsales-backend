package com.kynsof.calendar.application.query.schedule.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindScheduleByIdQuery  implements IQuery {

    private final UUID id;

    public FindScheduleByIdQuery(UUID id) {
        this.id = id;
    }

}
