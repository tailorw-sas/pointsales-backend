package com.kynsof.calendar.application.command.resource.addServices;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class AddServiceCommandHandler implements ICommandHandler<AddServiceCommand> {

    private final IResourceService resourceService;

    public AddServiceCommandHandler(IResourceService resourceService, IServiceService serviceService) {
        this.resourceService = resourceService;
    }

    @Override
    public void handle(AddServiceCommand command) {
        resourceService.create(new ResourceDto(command.getId(),command.getName(), command.getImage(), EResourceStatus.ACTIVE));
        resourceService.addServicesToResource(command.getId(),command.getServiceIds());
    }
}
