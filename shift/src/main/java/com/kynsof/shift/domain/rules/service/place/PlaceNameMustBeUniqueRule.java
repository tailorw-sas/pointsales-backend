package com.kynsof.shift.domain.rules.service.place;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.shift.domain.service.IPlaceService;

import java.util.UUID;

public class PlaceNameMustBeUniqueRule extends BusinessRule {

    private final IPlaceService service;

    private final String name;

    private final UUID id;

    public PlaceNameMustBeUniqueRule(IPlaceService service, String name, UUID id) {
        super(
                DomainErrorMessage.SERVICE_NAME_MUST_BY_UNIQUE, 
                new ErrorField("name", "The place name must be unique.")
        );
        this.service = service;
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByNameAndNotId(name, id) > 0;
    }

}
