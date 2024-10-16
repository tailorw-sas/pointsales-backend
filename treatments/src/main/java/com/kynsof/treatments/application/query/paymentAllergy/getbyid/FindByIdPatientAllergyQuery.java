package com.kynsof.treatments.application.query.paymentAllergy.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdPatientAllergyQuery implements IQuery {

    private final UUID id;

    public FindByIdPatientAllergyQuery(UUID id) {
        this.id = id;
    }

}
