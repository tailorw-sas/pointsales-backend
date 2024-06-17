package com.kynsoft.rrhh.application.command.device.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.rrhh.domain.dto.DeviceDto;
import com.kynsoft.rrhh.domain.interfaces.services.IDeviceService;
import org.springframework.stereotype.Component;

@Component
public class DeleteDeviceCommandHandler implements ICommandHandler<DeleteDeviceCommand> {

    private final IDeviceService service;

    public DeleteDeviceCommandHandler(IDeviceService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteDeviceCommand command) {
        DeviceDto delete = this.service.findById(command.getId());

        delete.setDeleted(true);

        service.delete(delete);
    }

}
