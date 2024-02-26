package com.kynsof.calendar.application.query.qualification.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindQualificationByIdQuery  implements IQuery {

    private UUID id;

    public FindQualificationByIdQuery(UUID id) {
        this.id = id;
    }

}
