package com.kynsof.treatments.application.query.vaccine.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdVaccineQuery implements IQuery {

    private final UUID id;

    public FindByIdVaccineQuery(UUID id) {
        this.id = id;
    }

}
