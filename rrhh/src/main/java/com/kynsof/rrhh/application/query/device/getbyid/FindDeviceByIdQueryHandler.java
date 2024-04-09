package com.kynsof.rrhh.application.query.device.getbyid;

import com.kynsof.rrhh.doman.dto.DeviceDto;
import com.kynsof.rrhh.doman.interfaces.services.IDeviceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindDeviceByIdQueryHandler implements IQueryHandler<FindDeviceByIdQuery, DeviceResponse>  {

    private final IDeviceService service;

    public FindDeviceByIdQueryHandler(IDeviceService service) {
        this.service = service;
    }

    @Override
    public DeviceResponse handle(FindDeviceByIdQuery query) {
        DeviceDto response = service.findById(query.getId());

        return new DeviceResponse(response);
    }
}
