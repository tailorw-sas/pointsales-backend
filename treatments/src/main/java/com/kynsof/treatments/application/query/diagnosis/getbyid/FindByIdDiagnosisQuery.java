package com.kynsof.treatments.application.query.diagnosis.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdDiagnosisQuery implements IQuery {

    private final UUID id;

    public FindByIdDiagnosisQuery(UUID id) {
        this.id = id;
    }

}
