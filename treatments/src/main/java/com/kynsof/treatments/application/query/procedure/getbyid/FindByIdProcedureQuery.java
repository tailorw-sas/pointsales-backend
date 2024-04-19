package com.kynsof.treatments.application.query.procedure.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdProcedureQuery implements IQuery {

    private final UUID id;

    public FindByIdProcedureQuery(UUID id) {
        this.id = id;
    }

}
