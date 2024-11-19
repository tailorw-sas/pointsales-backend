package com.kynsof.calendar.application.command.businessService.update;

import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessServicesCommandHandler implements ICommandHandler<UpdateBusinessServicesCommand> {

    private final IBusinessServicesService businessServicesService;


    public UpdateBusinessServicesCommandHandler(IBusinessServicesService businessServicesService) {

        this.businessServicesService = businessServicesService;

    }

    @Override
    public void handle(UpdateBusinessServicesCommand command) {

        BusinessServicesDto businessServicesDto = this.businessServicesService.findById(command.getBusinessServiceId());
        businessServicesDto.setPrice(command.getPrice());
        this.businessServicesService.update(businessServicesDto);


    }
}
