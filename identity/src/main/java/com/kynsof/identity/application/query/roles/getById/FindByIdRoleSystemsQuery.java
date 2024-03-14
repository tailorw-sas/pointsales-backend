package com.kynsof.identity.application.query.roles.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdRoleSystemsQuery implements IQuery {

    private final UUID id;

    public FindByIdRoleSystemsQuery(UUID id) {
        this.id = id;
    }

}
