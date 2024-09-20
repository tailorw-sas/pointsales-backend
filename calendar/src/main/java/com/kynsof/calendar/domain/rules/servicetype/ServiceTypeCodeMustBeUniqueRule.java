package com.kynsof.calendar.domain.rules.servicetype;

import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

import java.util.UUID;

public class ServiceTypeCodeMustBeUniqueRule extends BusinessRule {

    private final IServiceTypeService service;

    private final String code;

    private final UUID id;

    public ServiceTypeCodeMustBeUniqueRule(IServiceTypeService service, String code, UUID id) {
        super(
                DomainErrorMessage.SERVICE_TYPE_CODE_MUST_BY_UNIQUE,
                new ErrorField("code", DomainErrorMessage.SERVICE_TYPE_CODE_MUST_BY_UNIQUE.getReasonPhrase())
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
