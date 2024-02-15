package com.kynsof.scheduled.application.command.resource.update;

import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.service.IResourceService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateResourceCommandHandler implements ICommandHandler<UpdateResourceCommand> {

    private final IResourceService service;

    public UpdateResourceCommandHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateResourceCommand command) {
        service.update(new ResourceDto(
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
