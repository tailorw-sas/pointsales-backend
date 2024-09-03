package com.kynsof.identity.application.query.firebaseToken.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindFirebaseTokenByIdQuery implements IQuery {

    private final UUID id;

    public FindFirebaseTokenByIdQuery(UUID id) {
        this.id = id;
    }

}
