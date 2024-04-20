package com.kynsof.treatments.application.query.treatment.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdTreatmentQuery implements IQuery {

    private final UUID id;

    public FindByIdTreatmentQuery(UUID id) {
        this.id = id;
    }

}
