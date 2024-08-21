package com.kynsof.shift.application.command.resource.addServices;

import com.kynsof.shift.domain.dto.BusinessDto;
import com.kynsof.shift.domain.dto.BusinessResourceDto;
import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.shift.domain.dto.enumType.EResourceStatus;
import com.kynsof.shift.domain.service.IBusinessResourceService;
import com.kynsof.shift.domain.service.IBusinessService;
import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.shift.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddServiceCommandHandler implements ICommandHandler<AddServiceCommand> {

    private final IResourceService resourceService;
    private final IBusinessResourceService businessResourceService;
    private final IBusinessService businessService;

    public AddServiceCommandHandler(IResourceService resourceService, IServiceService serviceService, IBusinessResourceService businessResourceService, IBusinessService businessService) {
        this.resourceService = resourceService;
        this.businessResourceService = businessResourceService;
        this.businessService = businessService;
    }

    @Override
    public void handle(AddServiceCommand command) {
        resourceService.create(new ResourceDto(command.getId(),command.getName(), command.getImage(), EResourceStatus.ACTIVE, command.getCode()));
        resourceService.addServicesToResource(command.getId(),command.getServiceIds());
        BusinessDto businessDto = this.businessService.findById(command.getBusinessId());
        ResourceDto resourceDto = this.resourceService.findById(command.getId());
        businessResourceService.create(new BusinessResourceDto(UUID.randomUUID(), businessDto, resourceDto));
    }
}
