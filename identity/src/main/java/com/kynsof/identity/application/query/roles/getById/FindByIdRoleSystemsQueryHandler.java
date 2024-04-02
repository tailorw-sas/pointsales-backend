package com.kynsof.identity.application.query.roles.getById;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindByIdRoleSystemsQueryHandler implements IQueryHandler<FindByIdRoleSystemsQuery, RoleSystemsByIdResponse>  {

    private final IRoleService serviceImpl;

    public FindByIdRoleSystemsQueryHandler(IRoleService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public RoleSystemsByIdResponse handle(FindByIdRoleSystemsQuery query) {
        RoleDto roleDto = serviceImpl.findById(query.getId());

        return new RoleSystemsByIdResponse(roleDto);
    }
}
