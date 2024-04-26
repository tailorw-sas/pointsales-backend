package com.kynsof.treatments.application.query.medicine.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdMedicinesQuery implements IQuery {

    private final UUID id;

    public FindByIdMedicinesQuery(UUID id) {
        this.id = id;
    }

}
