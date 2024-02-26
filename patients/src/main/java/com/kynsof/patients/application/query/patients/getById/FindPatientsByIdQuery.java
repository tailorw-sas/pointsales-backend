package com.kynsof.patients.application.query.patients.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindPatientsByIdQuery  implements IQuery {

    private UUID id;

    public FindPatientsByIdQuery(UUID id) {
        this.id = id;
    }

}
