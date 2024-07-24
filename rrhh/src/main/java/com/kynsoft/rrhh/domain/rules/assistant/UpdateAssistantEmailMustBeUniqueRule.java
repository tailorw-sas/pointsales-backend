package com.kynsoft.rrhh.domain.rules.assistant;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;

import java.util.UUID;

public class UpdateAssistantEmailMustBeUniqueRule extends BusinessRule {

    private final IAssistantService service;

    private final String email;

    private final UUID id;

    public UpdateAssistantEmailMustBeUniqueRule(IAssistantService service, String email, UUID id) {
        super(
                DomainErrorMessage.MUST_BY_UNIQUE, 
                new ErrorField("email", "The email " + DomainErrorMessage.MUST_BY_UNIQUE.getReasonPhrase())
        );
        this.service = service;
        this.email = email;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByEmailAndNotId(email, id) > 0;
    }

}
