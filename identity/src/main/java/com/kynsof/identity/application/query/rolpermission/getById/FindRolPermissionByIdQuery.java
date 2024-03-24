package com.kynsof.identity.application.query.rolpermission.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindRolPermissionByIdQuery  implements IQuery {

    private final UUID id;

    public FindRolPermissionByIdQuery(UUID id) {
        this.id = id;
    }

}
