package com.kynsof.treatments.domain.rules.procedure;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.treatments.domain.service.IProcedureService;

import java.util.UUID;

public class ProcedureNameMustBeUniqueRule extends BusinessRule {

    private final IProcedureService service;

    private final String name;

    private final UUID id;

    public ProcedureNameMustBeUniqueRule(IProcedureService service, String name, UUID id) {
        super(
                DomainErrorMessage.PROCEDURE_NAME_MUST_BY_UNIQUE,
                new ErrorField("name", DomainErrorMessage.PROCEDURE_NAME_MUST_BY_UNIQUE.getReasonPhrase())
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
