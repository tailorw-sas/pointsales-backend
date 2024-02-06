package com.kynsof.scheduled.application.query.schedule.getbyid;

import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindScheduleByIdQuery  implements IQuery {

    private UUID id;

    public FindScheduleByIdQuery(UUID id) {
        this.id = id;
    }

}
