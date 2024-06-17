package com.kynsoft.rrhh.application.query.doctor.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdDoctorQuery implements IQuery {

    private final UUID id;

    public FindByIdDoctorQuery(UUID id) {
        this.id = id;
    }

}
