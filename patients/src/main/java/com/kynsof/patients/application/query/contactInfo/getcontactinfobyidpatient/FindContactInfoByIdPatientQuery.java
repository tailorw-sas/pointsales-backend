package com.kynsof.patients.application.query.contactInfo.getcontactinfobyidpatient;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindContactInfoByIdPatientQuery implements IQuery {

    private final UUID id;

    public FindContactInfoByIdPatientQuery(UUID id) {
        this.id = id;
    }

}
