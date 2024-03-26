package com.kynsof.calendar.application.command.service.update;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateServiceCommandHandler  implements ICommandHandler<UpdateServiceCommand> {

    private final IServiceService service;
    private final IServiceTypeService serviceTypeService;

    public UpdateServiceCommandHandler(IServiceService service, IServiceTypeService serviceTypeService) {
        this.service = service;
        this.serviceTypeService = serviceTypeService;
    }

    @Override
    public void handle(UpdateServiceCommand command) {
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getServiceTypeId());
       service.update(new ServiceDto(
               command.getId(), 
               serviceTypeDto,
               command.getPicture(), 
               command.getName(), 
               command.getNormalAppointmentPrice(), 
               command.getExpressAppointmentPrice(), 
               command.getDescription()
       ));
    }
}