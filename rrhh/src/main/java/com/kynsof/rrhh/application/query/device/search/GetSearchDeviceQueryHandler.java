package com.kynsof.rrhh.application.query.device.search;

import com.kynsof.rrhh.doman.interfaces.services.IDeviceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSearchDeviceQueryHandler implements IQueryHandler<GetSearchDeviceQuery, PaginatedResponse>{
    private final IDeviceService service;

    public GetSearchDeviceQueryHandler(IDeviceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchDeviceQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
