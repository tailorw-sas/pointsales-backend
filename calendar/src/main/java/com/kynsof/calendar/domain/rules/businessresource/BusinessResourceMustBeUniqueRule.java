package com.kynsof.calendar.domain.rules.businessresource;

import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import java.util.UUID;

public class BusinessResourceMustBeUniqueRule extends BusinessRule {

    private final IBusinessResourceService service;

    private final UUID business;

    private final UUID resource;

    public BusinessResourceMustBeUniqueRule(IBusinessResourceService service, UUID business, UUID resource) {
        super(
                DomainErrorMessage.BUSINESS_NAME_MUST_BY_UNIQUE, 
                new ErrorField("resource", "Existing relationship.")
        );
        this.service = service;
        this.business = business;
        this.resource = resource;
    }

    @Override
    public boolean isBroken() {
        return this.service.countBusinessResourceByBusinessIdAndResourceId(business, resource) > 0;
    }

}
