package com.kynsof.patients.application.query.currentMedication.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdCurrentMedicationIQuery implements IQuery {

    private final UUID id;

    public FindByIdCurrentMedicationIQuery(UUID id) {
        this.id = id;
    }

}
