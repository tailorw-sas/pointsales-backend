package com.kynsoft.rrhh.application.query.device.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.rrhh.domain.dto.DeviceDto;
import com.kynsoft.rrhh.domain.interfaces.services.IDeviceService;
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
