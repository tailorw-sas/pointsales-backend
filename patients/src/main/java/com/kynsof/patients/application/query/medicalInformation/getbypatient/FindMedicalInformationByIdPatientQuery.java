package com.kynsof.patients.application.query.medicalInformation.getbypatient;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindMedicalInformationByIdPatientQuery implements IQuery {

    private final UUID id;

    public FindMedicalInformationByIdPatientQuery(UUID id) {
        this.id = id;
    }

}
