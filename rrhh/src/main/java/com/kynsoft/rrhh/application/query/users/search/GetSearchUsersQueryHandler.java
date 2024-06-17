package com.kynsoft.rrhh.application.query.users.search;

import com.kynsoft.rrhh.domain.interfaces.services.IUserSystemService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchUsersQueryHandler implements IQueryHandler<GetSearchUsersQuery, PaginatedResponse>{
    private final IUserSystemService service;

    public GetSearchUsersQueryHandler(IUserSystemService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchUsersQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
