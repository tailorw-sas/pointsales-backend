package com.kynsof.rrhh.application.command.device.update;

import com.kynsof.rrhh.doman.dto.BusinessDto;
import com.kynsof.rrhh.doman.dto.DeviceDto;
import com.kynsof.rrhh.doman.interfaces.services.IBusinessService;
import com.kynsof.rrhh.doman.interfaces.services.IDeviceService;
import com.kynsof.rrhh.doman.rules.device.DeviceIpValidateRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateDeviceCommandHandler implements ICommandHandler<UpdateDeviceCommand> {

    private final IDeviceService service;
    private final IBusinessService businessService;

    public UpdateDeviceCommandHandler(IDeviceService service, IBusinessService businessService) {
        this.service = service;
        this.businessService = businessService;
    }

    @Override
    public void handle(UpdateDeviceCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule(command.getId(), "Device.id", "Device ID cannot be null."));
        DeviceDto update = this.service.findById(command.getId());

        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusinessId(), "Device.business.id", "Device.business ID cannot be null."));
        BusinessDto business = this.businessService.findById(command.getBusinessId());
        update.setBusiness(business);

        if (command.getIp() != null && !command.getIp().isEmpty()) {
            RulesChecker.checkRule(new DeviceIpValidateRule(command.getIp()));
            update.setIp(command.getIp());
        }
        UpdateIfNotNull.updateIfStringNotNull(update::setSerialId, command.getSerialId());

        service.update(update);
    }
}
