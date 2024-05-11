package com.kynsof.treatments.domain.rules.procedure;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.treatments.domain.service.IProcedureService;

import java.util.UUID;

public class ProcedureCodeMustBeUniqueRule extends BusinessRule {

    private final IProcedureService service;

    private final String code;

    private final UUID id;

    public ProcedureCodeMustBeUniqueRule(IProcedureService service, String code, UUID id) {
        super(
                DomainErrorMessage.PROCEDURE_CODE_MUST_BY_UNIQUE, 
                new ErrorField("code", "The procedure code must be unique.")
        );
        this.service = service;
        this.code = code;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByCodeAndNotId(code, id) > 0;
    }

}
