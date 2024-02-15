package com.kynsof.patients.application.query.contactInfo.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdContactInfoQuery implements IQuery {

    private final UUID id;

    public FindByIdContactInfoQuery(UUID id) {
        this.id = id;
    }

}
