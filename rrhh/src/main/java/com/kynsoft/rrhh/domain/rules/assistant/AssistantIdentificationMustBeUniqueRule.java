package com.kynsoft.rrhh.domain.rules.assistant;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;

public class AssistantIdentificationMustBeUniqueRule extends BusinessRule {

    private final IAssistantService service;

    private final String identification;

    public AssistantIdentificationMustBeUniqueRule(IAssistantService service, String identification) {
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
