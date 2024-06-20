package com.kynsoft.rrhh.domain.rules.assistant;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;

public class AssistantEmailMustBeUniqueRule extends BusinessRule {

    private final IAssistantService service;

    private final String email;

    public AssistantEmailMustBeUniqueRule(IAssistantService service, String email) {
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
