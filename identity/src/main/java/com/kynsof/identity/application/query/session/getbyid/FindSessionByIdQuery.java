package com.kynsof.identity.application.query.session.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindSessionByIdQuery implements IQuery {

    private final UUID id;

    public FindSessionByIdQuery(UUID id) {
        this.id = id;
    }
}
