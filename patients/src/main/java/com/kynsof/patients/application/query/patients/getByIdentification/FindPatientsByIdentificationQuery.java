package com.kynsof.patients.application.query.patients.getByIdentification;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class FindPatientsByIdentificationQuery implements IQuery {

    private final String identification;

    public FindPatientsByIdentificationQuery(String id) {
        this.identification = id;
    }

}
