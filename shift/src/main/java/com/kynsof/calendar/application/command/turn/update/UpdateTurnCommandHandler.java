package com.kynsof.calendar.application.command.turn.update;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.ITurnService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateTurnCommandHandler implements ICommandHandler<UpdateTurnCommand> {

    private final ITurnService turnService;
    private final IBusinessService businessService;
    private final IResourceService resourceService;
    private final IServiceService serviceService;

    public UpdateTurnCommandHandler(ITurnService turnService, IBusinessService businessService, IResourceService resourceService, IServiceService serviceService) {
        this.turnService = turnService;
        this.businessService = businessService;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(UpdateTurnCommand command) {
        TurnDto turnDto = turnService.findById(command.getId());
        BusinessDto businessDto = businessService.findById(command.getBusiness());
        ResourceDto resourceDto = command.getDoctor() != null ? resourceService.findById(command.getDoctor()) : null;
        ServiceDto serviceDto = serviceService.findByIds(command.getService());

        turnDto.setBusiness(businessDto);
        turnDto.setDoctor(resourceDto);
        turnDto.setIdentification(command.getIdentification());
        turnDto.setServices(serviceDto);
        turnDto.setStatus(command.getStatus());
        turnService.update(turnDto);
    }
}
