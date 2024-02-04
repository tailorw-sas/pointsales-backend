package com.kynsof.patients.application.query.allergy.getById;

import com.kynsof.patients.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdAllergyQuery implements IQuery {

    private final UUID id;

    public FindByIdAllergyQuery(UUID id) {
        this.id = id;
    }

}
