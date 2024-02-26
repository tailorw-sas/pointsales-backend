package com.kynsof.patients.application.query.insuarance.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdInsuranceQuery implements IQuery {

    private final UUID id;

    public FindByIdInsuranceQuery(UUID id) {
        this.id = id;
    }

}
