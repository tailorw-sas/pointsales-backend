package com.kynsof.identity.domain.rules.paymentdev;

import com.kynsof.identity.domain.interfaces.service.IPaymentDevService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

import java.util.UUID;

public class ModuleReferenceMustBeUniqueRule extends BusinessRule {

    private final IPaymentDevService service;

    private final String reference;

    private final UUID id;

    public ModuleReferenceMustBeUniqueRule(IPaymentDevService service, String reference, UUID id) {
        super(
                DomainErrorMessage.MODULE_NAME_MUST_BY_UNIQUE, 
                new ErrorField("reference", "The Payment to Developers reference must be unique.")
        );
        this.service = service;
        this.reference = reference;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByReferenceAndNotId(reference, id) > 0;
    }

}
