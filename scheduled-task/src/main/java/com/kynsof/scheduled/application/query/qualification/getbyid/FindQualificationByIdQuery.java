package com.kynsof.scheduled.application.query.qualification.getbyid;

import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindQualificationByIdQuery  implements IQuery {

    private UUID id;

    public FindQualificationByIdQuery(UUID id) {
        this.id = id;
    }

}
