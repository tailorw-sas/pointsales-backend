package com.kynsof.shift.application.command.resource.update;

import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.shift.domain.service.IResourceService;
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

        ResourceDto _resource = service.findById(command.getId());
        _resource.setName(command.getName());
        _resource.setImage(command.getImage());
        _resource.setStatus(command.getStatus());
        service.update(_resource);
        service.addServicesToResource(command.getId(), command.getServiceIds());


    }
}
