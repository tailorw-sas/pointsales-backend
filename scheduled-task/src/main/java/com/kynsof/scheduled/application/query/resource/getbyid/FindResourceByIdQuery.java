package com.kynsof.scheduled.application.query.resource.getbyid;

import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindResourceByIdQuery  implements IQuery {

    private UUID id;

    public FindResourceByIdQuery(UUID id) {
        this.id = id;
    }

}
