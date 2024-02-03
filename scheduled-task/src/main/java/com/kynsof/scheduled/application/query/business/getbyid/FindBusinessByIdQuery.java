package com.kynsof.scheduled.application.query.business.getbyid;

import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindBusinessByIdQuery  implements IQuery {

    private UUID id;

    public FindBusinessByIdQuery(UUID id) {
        this.id = id;
    }

}
