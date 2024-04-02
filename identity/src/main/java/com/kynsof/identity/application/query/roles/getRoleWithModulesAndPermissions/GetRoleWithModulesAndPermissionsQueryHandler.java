package com.kynsof.identity.application.query.roles.getRoleWithModulesAndPermissions;

import com.kynsof.identity.domain.dto.roleDto.RoleWithModulesResponse;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GetRoleWithModulesAndPermissionsQueryHandler implements IQueryHandler<GetRoleWithModulesAndPermissionsQuery, RoleWithModulesResponse>  {

    private final IRoleService serviceImpl;

    public GetRoleWithModulesAndPermissionsQueryHandler(IRoleService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public RoleWithModulesResponse handle(GetRoleWithModulesAndPermissionsQuery query) {
        return serviceImpl.getRoleWithModulesAndPermissions(query.getId());
    }
}
