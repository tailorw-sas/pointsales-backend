package com.kynsof.scheduled.application.command.resource.create;

import com.kynsof.scheduled.application.command.business.create.*;
import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateResourceCommandHandler implements ICommandHandler<CreateResourceCommand> {

    private final ResocurceServiceImpl serviceImpl;

    public CreateResourceCommandHandler(ResocurceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateResourceCommand command) {
        serviceImpl.create(new ResourceDto(
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
