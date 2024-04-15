package com.kynsof.calendar.application.query.businessresource.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindBusinessResourceByIdQuery  implements IQuery {

    private UUID id;

    public FindBusinessResourceByIdQuery(UUID id) {
        this.id = id;
    }

}
