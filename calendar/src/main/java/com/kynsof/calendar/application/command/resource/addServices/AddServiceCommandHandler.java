package com.kynsof.calendar.application.command.resource.addServices;

import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class AddServiceCommandHandler implements ICommandHandler<AddServiceCommand> {

    private final IResourceService resourceService;
    private final IServiceService serviceService;

    public AddServiceCommandHandler(IResourceService resourceService, IServiceService serviceService) {
        this.resourceService = resourceService;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(AddServiceCommand command) {
        resourceService.addServicesToResource(command.getId(),command.getServiceIds());
    }
}
