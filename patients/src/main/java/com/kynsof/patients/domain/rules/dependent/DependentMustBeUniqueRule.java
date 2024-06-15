package com.kynsof.patients.domain.rules.dependent;

import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

import java.util.UUID;

public class DependentMustBeUniqueRule extends BusinessRule {

    private final IPatientsService service;

    private final String identification;

    private final UUID id;

    public DependentMustBeUniqueRule(IPatientsService service, String identification, UUID id) {
        super(
                DomainErrorMessage.PATIENT_IDENTIFICATION_MUST_BY_UNIQUE, 
                new ErrorField("identification", "The patients identification must be unique.")
        );
        this.service = service;
        this.identification = identification;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByIdentificationAndNotId(identification, id) > 0;
    }

}
