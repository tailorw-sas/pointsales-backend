package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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
