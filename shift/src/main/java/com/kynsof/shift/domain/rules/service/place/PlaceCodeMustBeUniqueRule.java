package com.kynsof.shift.domain.rules.service.place;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.shift.domain.service.IPlaceService;

import java.util.UUID;

public class PlaceCodeMustBeUniqueRule extends BusinessRule {

    private final IPlaceService service;

    private final String code;

    private final UUID id;

    public PlaceCodeMustBeUniqueRule(IPlaceService service, String code, UUID id) {
        super(
                DomainErrorMessage.SERVICE_NAME_MUST_BY_UNIQUE, 
                new ErrorField("name", "The place name must be unique.")
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
