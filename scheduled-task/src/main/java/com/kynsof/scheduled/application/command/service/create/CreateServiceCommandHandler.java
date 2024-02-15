package com.kynsof.scheduled.application.command.service.create;

import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.domain.service.IServiceService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceCommandHandler implements ICommandHandler<CreateServiceCommand> {

    private final IServiceService service;

    public CreateServiceCommandHandler(IServiceService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateServiceCommand command) {
        service.create(new ServiceDto(
                command.getId(), 
                command.getType(), 
                command.getPicture(), 
                command.getName(), 
                command.getNormalAppointmentPrice(), 
                command.getExpressAppointmentPrice(), 
                command.getDescription()));
    }
}
