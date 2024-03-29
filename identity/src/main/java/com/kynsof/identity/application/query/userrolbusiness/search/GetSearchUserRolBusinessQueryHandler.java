package com.kynsof.identity.application.query.userrolbusiness.search;

import com.kynsof.identity.domain.interfaces.service.IUserRoleBusinessService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchUserRolBusinessQueryHandler implements IQueryHandler<GetSearchUserRolBusinessQuery, PaginatedResponse> {

    private final IUserRoleBusinessService serviceImpl;

    public GetSearchUserRolBusinessQueryHandler(IUserRoleBusinessService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchUserRolBusinessQuery query) {

        return this.serviceImpl.search(query.getPageable(), query.getFilter());
    }
}
