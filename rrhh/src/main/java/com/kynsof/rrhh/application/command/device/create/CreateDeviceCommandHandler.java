package com.kynsof.rrhh.application.command.device.create;

import com.kynsof.rrhh.domain.dto.BusinessDto;
import com.kynsof.rrhh.domain.dto.DeviceDto;
import com.kynsof.rrhh.domain.interfaces.services.IBusinessService;
import com.kynsof.rrhh.domain.interfaces.services.IDeviceService;
import com.kynsof.rrhh.domain.rules.device.DeviceIpValidateRule;
import com.kynsof.rrhh.domain.rules.device.DeviceSerialMustBeNullRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import org.springframework.stereotype.Component;

@Component
public class CreateDeviceCommandHandler implements ICommandHandler<CreateDeviceCommand> {

    private final IDeviceService service;
    private final IBusinessService businessService;

    public CreateDeviceCommandHandler(IDeviceService service, IBusinessService businessService) {
        this.service = service;
        this.businessService = businessService;
    }

    @Override
    public void handle(CreateDeviceCommand command) {
        RulesChecker.checkRule(new DeviceIpValidateRule(command.getIp()));
        RulesChecker.checkRule(new DeviceSerialMustBeNullRule(command.getSerialId()));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusinessId(), "Device.business.id", "Device.business ID cannot be null."));
        BusinessDto business = this.businessService.findById(command.getBusinessId());

        DeviceDto deviceSave = new DeviceDto(command.getId(), command.getSerialId(), command.getIp(), business);
        deviceSave.setDeleted(false);
        service.create(deviceSave);
    }
}
