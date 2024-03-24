package com.kynsof.identity.application.query.rolpermission.search;

import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchRolPermissionQueryHandler implements IQueryHandler<GetSearchRolPermissionQuery, PaginatedResponse> {

    private final IRolePermissionService service;

    public GetSearchRolPermissionQueryHandler(IRolePermissionService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchRolPermissionQuery query) {

        return this.service.search(query.getPageable(), query.getFilter());
    }
}
