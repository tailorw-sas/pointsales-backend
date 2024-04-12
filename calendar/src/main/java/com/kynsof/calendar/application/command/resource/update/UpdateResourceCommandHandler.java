package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateResourceCommandHandler implements ICommandHandler<UpdateResourceCommand> {

    private final IResourceService service;

    public UpdateResourceCommandHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateResourceCommand command) {

        //TODO yannier las reglas de validacion
        ResourceDto _resource = service.findById(command.getId());

        _resource.setRegistrationNumber(command.getRegistrationNumber());
        _resource.setLanguage(command.getLanguage());
        command.setExpressAppointments(command.getExpressAppointments());
        service.update(_resource
        );
    }
}
