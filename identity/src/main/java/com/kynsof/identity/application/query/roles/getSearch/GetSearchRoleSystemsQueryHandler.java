package com.kynsof.identity.application.query.roles.getSearch;

import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchRoleSystemsQueryHandler implements IQueryHandler<GetSearchRoleSystemsQuery, PaginatedResponse>{

    private final IRoleService serviceImpl;

    public GetSearchRoleSystemsQueryHandler(IRoleService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchRoleSystemsQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
