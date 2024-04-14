package com.kynsof.treatments.domain.rules.vaccine;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.treatments.domain.service.IVaccineService;
import java.util.UUID;

public class VaccineNameMustBeUniqueRule extends BusinessRule {

    private final IVaccineService service;

    private final String name;

    private final UUID id;

    public VaccineNameMustBeUniqueRule(IVaccineService service, String name, UUID id) {
        super(
                DomainErrorMessage.VACCINE_MUST_BY_UNIQUE, 
                new ErrorField("name", "The vaccine name must be unique.")
        );
        this.service = service;
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByNameAndNotId(name, id) > 0;
    }

}
