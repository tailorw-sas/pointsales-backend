package com.kynsof.scheduled.application.query.service.getbyid;

import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindServiceByIdQuery  implements IQuery {

    private UUID id;

    public FindServiceByIdQuery(UUID id) {
        this.id = id;
    }

}
