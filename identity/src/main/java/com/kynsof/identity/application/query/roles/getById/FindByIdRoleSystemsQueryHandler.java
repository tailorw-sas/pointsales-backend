package com.kynsof.identity.application.query.roles.getById;

import com.kynsof.identity.application.query.roles.getSearch.RoleSystemsResponse;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindByIdRoleSystemsQueryHandler implements IQueryHandler<FindByIdRoleSystemsQuery, RoleSystemsResponse>  {

    private final IRoleService serviceImpl;

    public FindByIdRoleSystemsQueryHandler(IRoleService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public RoleSystemsResponse handle(FindByIdRoleSystemsQuery query) {
        RoleDto userSystemDto = serviceImpl.findById(query.getId());

        return new RoleSystemsResponse(userSystemDto);
    }
}
