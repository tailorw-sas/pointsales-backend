package com.kynsof.patients.application.query.additionalInfo.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdAdditionalInfoQuery implements IQuery {

    private final UUID id;

    public FindByIdAdditionalInfoQuery(UUID id) {
        this.id = id;
    }

}
