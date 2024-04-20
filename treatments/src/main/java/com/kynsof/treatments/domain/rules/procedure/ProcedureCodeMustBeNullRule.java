package com.kynsof.treatments.domain.rules.procedure;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class ProcedureCodeMustBeNullRule extends BusinessRule {

    private final String code;

    public ProcedureCodeMustBeNullRule(String code) {
        super(
                DomainErrorMessage.PROCEDURE_CODE_MUST_BY_UNIQUE, 
                new ErrorField("code", "The code of the procedure cannot be empty.")
        );
        this.code = code;
    }

    @Override
    public boolean isBroken() {
        return this.code == null || this.code.isEmpty();
    }

}
