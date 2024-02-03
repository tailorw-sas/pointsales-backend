package com.kynsof.patients.application.query.medicalInformation.getById;

import com.kynsof.patients.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdMedicalInformationQuery implements IQuery {

    private final UUID id;

    public FindByIdMedicalInformationQuery(UUID id) {
        this.id = id;
    }

}
