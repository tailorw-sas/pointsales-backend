package com.kynsof.identity.domain.rules.businessmodule;

import com.kynsof.identity.domain.interfaces.service.IBusinessModuleService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import java.util.UUID;

public class BusinessModuleMustBeUniqueRule extends BusinessRule {

    private final IBusinessModuleService service;

    private final UUID businessId;

    private final UUID moduleId;

    private final UUID id;

    public BusinessModuleMustBeUniqueRule(IBusinessModuleService service, UUID businessId, UUID moduleId, UUID id) {
        super(
                DomainErrorMessage.MODULE_NAME_MUST_BY_UNIQUE, 
                new ErrorField("module", "La relacion ya existe.")
        );
        this.service = service;
        this.businessId = businessId;
        this.moduleId = moduleId;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByBussinessIdAndModuleIdAndNotId(businessId, moduleId, id) > 0;
    }

}
