package com.kynsof.treatments.application.query.patientVaccine.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdPatientVaccineQuery implements IQuery {

    private final UUID id;

    public FindByIdPatientVaccineQuery(UUID id) {
        this.id = id;
    }

}
