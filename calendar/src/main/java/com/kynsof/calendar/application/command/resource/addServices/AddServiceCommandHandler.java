package com.kynsof.calendar.application.command.resource.addServices;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        ResourceDto resourceDto = resourceService.findById(command.getId());
        List<ServiceDto> serviceDtos = new ArrayList<>();
        for (UUID serviceId : command.getServiceIds()) {
            ServiceDto serviceDto = serviceService.findById(serviceId);
            serviceDtos.add(serviceDto);
        }
        resourceDto.setServices(serviceDtos);
        resourceService.update(resourceDto);
    }
}
