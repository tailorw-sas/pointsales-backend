package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceCommandHandler implements ICommandHandler<CreateServiceCommand> {

    private final IServiceService service;
    private final IServiceTypeService serviceTypeService;


    public CreateServiceCommandHandler(IServiceService service, IServiceTypeService serviceTypeService) {
        this.service = service;
        this.serviceTypeService = serviceTypeService;
    }

    @Override
    public void handle(CreateServiceCommand command) {
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getServiceTypeId());
        service.create(new ServiceDto(
                command.getId(), 
                serviceTypeDto,
                command.getPicture(), 
                command.getName(), 
                command.getNormalAppointmentPrice(), 
                command.getExpressAppointmentPrice(), 
                command.getDescription()));
    }
}
