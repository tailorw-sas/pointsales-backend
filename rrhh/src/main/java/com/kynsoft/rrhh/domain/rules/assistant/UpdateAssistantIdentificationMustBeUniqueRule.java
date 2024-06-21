package com.kynsoft.rrhh.domain.rules.assistant;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import java.util.UUID;

public class UpdateAssistantIdentificationMustBeUniqueRule extends BusinessRule {

    private final IAssistantService service;

    private final String identification;

    private final UUID id;

    public UpdateAssistantIdentificationMustBeUniqueRule(IAssistantService service, String identification, UUID id) {
        super(
                DomainErrorMessage.MUST_BY_UNIQUE, 
                new ErrorField("identification", "The identification " + DomainErrorMessage.MUST_BY_UNIQUE.getReasonPhrase())
        );
        this.service = service;
        this.identification = identification;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByIdentificationAndNotId(identification, id) > 0;
    }

}
