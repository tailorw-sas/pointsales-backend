package com.kynsof.treatments.domain.rules.medicines;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.treatments.domain.service.IMedicinesService;

import java.util.UUID;

public class MedicinesNameMustBeUniqueRule extends BusinessRule {

    private final IMedicinesService service;

    private final String name;

    private final UUID id;

    public MedicinesNameMustBeUniqueRule(IMedicinesService service, String name, UUID id) {
        super(
                DomainErrorMessage.MEDICINES_NAME_MUST_BY_UNIQUE,
                new ErrorField("name", DomainErrorMessage.MEDICINES_NAME_MUST_BY_UNIQUE.getReasonPhrase())
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
