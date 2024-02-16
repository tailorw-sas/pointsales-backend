package com.kynsof.calendar.application.command.service.update;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateServiceCommandHandler  implements ICommandHandler<UpdateServiceCommand> {

    private final IServiceService service;

    public UpdateServiceCommandHandler(IServiceService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateServiceCommand command) {
       service.update(new ServiceDto(
               command.getId(), 
               command.getType(), 
               command.getPicture(), 
               command.getName(), 
               command.getNormalAppointmentPrice(), 
               command.getExpressAppointmentPrice(), 
               command.getDescription()
       ));
    }
}