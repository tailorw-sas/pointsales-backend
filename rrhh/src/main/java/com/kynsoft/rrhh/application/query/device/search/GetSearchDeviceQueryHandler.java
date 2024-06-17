package com.kynsoft.rrhh.application.query.device.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.rrhh.domain.interfaces.services.IDeviceService;
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
