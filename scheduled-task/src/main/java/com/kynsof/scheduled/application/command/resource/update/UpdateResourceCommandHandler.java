package com.kynsof.scheduled.application.command.resource.update;

import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UpdateResourceCommandHandler implements ICommandHandler<UpdateResourceCommand> {

    private final ResocurceServiceImpl serviceImpl;

    public UpdateResourceCommandHandler(ResocurceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateResourceCommand command) {
        serviceImpl.update(new ResourceDto(
                command.getId(),
                command.getPicture(),
                command.getName(),
                command.getRegistrationNumber(),
                command.getLanguage(),
                command.getStatus(),                
                command.getExpressAppointments()
        ));
    }
}
