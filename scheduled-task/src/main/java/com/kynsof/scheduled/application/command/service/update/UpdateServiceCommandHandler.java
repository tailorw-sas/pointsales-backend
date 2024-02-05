package com.kynsof.scheduled.application.command.service.update;

import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ServiceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UpdateServiceCommandHandler  implements ICommandHandler<UpdateServiceCommand> {

    private final ServiceServiceImpl serviceImpl;

    public UpdateServiceCommandHandler(ServiceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateServiceCommand command) {
       serviceImpl.update(new ServiceDto(
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