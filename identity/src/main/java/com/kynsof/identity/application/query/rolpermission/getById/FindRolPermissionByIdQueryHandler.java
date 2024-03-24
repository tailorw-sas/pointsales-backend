package com.kynsof.identity.application.query.rolpermission.getById;

import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindRolPermissionByIdQueryHandler implements IQueryHandler<FindRolPermissionByIdQuery, RolPermissionResponse>  {

    private final IRolePermissionService service;

    public FindRolPermissionByIdQueryHandler(IRolePermissionService service) {
        this.service = service;
    }

    @Override
    public RolPermissionResponse handle(FindRolPermissionByIdQuery query) {
        RolePermissionDto response = service.findById(query.getId());

        return new RolPermissionResponse(response);
    }
}
