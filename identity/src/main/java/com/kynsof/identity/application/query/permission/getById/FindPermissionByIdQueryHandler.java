package com.kynsof.identity.application.query.permission.getById;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindPermissionByIdQueryHandler implements IQueryHandler<FindPermissionByIdQuery, PermissionrResponse>  {

    private final IPermissionService service;

    public FindPermissionByIdQueryHandler(IPermissionService service) {
        this.service = service;
    }

    @Override
    public PermissionrResponse handle(FindPermissionByIdQuery query) {
        PermissionDto response = service.findById(query.getId());

        return new PermissionrResponse(response);
    }
}
