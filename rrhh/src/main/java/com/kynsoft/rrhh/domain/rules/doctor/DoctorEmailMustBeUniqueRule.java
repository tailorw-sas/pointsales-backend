package com.kynsoft.rrhh.domain.rules.doctor;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;

public class DoctorEmailMustBeUniqueRule extends BusinessRule {

    private final IDoctorService service;

    private final String email;

    public DoctorEmailMustBeUniqueRule(IDoctorService service, String email) {
        super(
                DomainErrorMessage.MUST_BY_UNIQUE, 
                new ErrorField("email", "The email " + DomainErrorMessage.MUST_BY_UNIQUE.getReasonPhrase())
        );
        this.service = service;
        this.email = email;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByEmailAndNotId(email) > 0;
    }

}
