package com.kynsoft.notification.application.query.tenant.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.service.ITenantService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchTenantQueryHandler implements IQueryHandler<GetSearchTenantQuery, PaginatedResponse>{

    private final ITenantService serviceImpl;

    public GetSearchTenantQueryHandler(ITenantService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchTenantQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
