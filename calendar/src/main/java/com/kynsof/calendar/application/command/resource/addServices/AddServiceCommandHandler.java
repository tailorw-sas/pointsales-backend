package com.kynsof.calendar.application.command.resource.addServices;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddServiceCommandHandler implements ICommandHandler<AddServiceCommand> {

    private final IResourceService resourceService;
    private final IBusinessResourceService businessResourceService;
    private final IBusinessService businessService;

    public AddServiceCommandHandler(IResourceService resourceService, IBusinessResourceService businessResourceService,
                                    IBusinessService businessService) {
        this.resourceService = resourceService;
        this.businessResourceService = businessResourceService;
        this.businessService = businessService;
    }

    @Override
    public void handle(AddServiceCommand command) {
        resourceService.create(new ResourceDto(command.getId(),command.getName(), command.getImage(), EResourceStatus.ACTIVE));
        resourceService.addServicesToResource(command.getId(),command.getServiceIds());
        BusinessDto businessDto = this.businessService.findById(command.getBusinessId());
        ResourceDto resourceDto = this.resourceService.findById(command.getId());
        businessResourceService.create(new BusinessResourceDto(UUID.randomUUID(), businessDto, resourceDto));
    }
}
