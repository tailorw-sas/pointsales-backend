package com.kynsoft.rrhh.domain.rules.doctor;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;

public class DoctorIdentificationMustBeUniqueRule extends BusinessRule {

    private final IDoctorService service;

    private final String identification;

    public DoctorIdentificationMustBeUniqueRule(IDoctorService service, String identification) {
        super(
                DomainErrorMessage.MUST_BY_UNIQUE, 
                new ErrorField("identification", "The identification " + DomainErrorMessage.MUST_BY_UNIQUE.getReasonPhrase())
        );
        this.service = service;
        this.identification = identification;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByIdentificationAndNotId(identification) > 0;
    }

}
