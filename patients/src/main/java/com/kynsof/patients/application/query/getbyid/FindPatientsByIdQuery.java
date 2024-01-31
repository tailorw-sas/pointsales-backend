package com.kynsof.patients.application.query.getbyid;

import com.kynsof.patients.domain.bus.query.IQuery;
import java.util.UUID;

public class FindPatientsByIdQuery  implements IQuery {

    private UUID id;

    public FindPatientsByIdQuery(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
