package com.kynsof.identity.application.query.roles.getRoleWithModulesAndPermissions;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GetRoleWithModulesAndPermissionsQuery implements IQuery {

    private final UUID id;

    public GetRoleWithModulesAndPermissionsQuery(UUID id) {
        this.id = id;
    }

}
