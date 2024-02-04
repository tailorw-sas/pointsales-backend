package com.kynsof.scheduled.application.command.service.create;

import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ServiceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceCommandHandler implements ICommandHandler<CreateServiceCommand> {

    private final ServiceServiceImpl serviceImpl;

    public CreateServiceCommandHandler(ServiceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateServiceCommand command) {
        serviceImpl.create(new ServiceDto(
                command.getId(), 
                command.getType(), 
                command.getPicture(), 
                command.getName(), 
                command.getNormalAppointmentPrice(), 
                command.getExpressAppointmentPrice(), 
                command.getDescription()));
    }
}
