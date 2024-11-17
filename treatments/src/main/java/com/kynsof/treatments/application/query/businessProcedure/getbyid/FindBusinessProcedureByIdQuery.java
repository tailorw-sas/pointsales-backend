package com.kynsof.treatments.application.query.businessProcedure.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindBusinessProcedureByIdQuery implements IQuery {

    private UUID id;

    public FindBusinessProcedureByIdQuery(UUID id) {
        this.id = id;
    }

}
