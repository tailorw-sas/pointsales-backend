package com.kynsoft.rrhh.application.query.users.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdUserSystemsQuery implements IQuery {

    private final UUID id;

    public FindByIdUserSystemsQuery(UUID id) {
        this.id = id;
    }

}
