package com.kynsof.rrhh.application.query.device.getusersbyiddevice;

import com.kynsof.rrhh.doman.interfaces.services.IDeviceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class FindUsersByIdDeviceQueryHandler implements IQueryHandler<FindUsersByIdDeviceQuery, PaginatedResponse> {

    private final IDeviceService service;

    public FindUsersByIdDeviceQueryHandler(IDeviceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindUsersByIdDeviceQuery query) {

        return this.service.findUsersByDeviceId(query.getId(), query.getPageable());
    }
}
