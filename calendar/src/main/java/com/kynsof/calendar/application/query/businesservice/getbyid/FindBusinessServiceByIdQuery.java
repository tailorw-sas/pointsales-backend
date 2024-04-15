package com.kynsof.calendar.application.query.businesservice.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindBusinessServiceByIdQuery  implements IQuery {

    private UUID id;

    public FindBusinessServiceByIdQuery(UUID id) {
        this.id = id;
    }

}
