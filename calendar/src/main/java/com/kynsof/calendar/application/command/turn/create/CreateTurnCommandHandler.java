package com.kynsof.calendar.application.command.turn.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.domain.service.ITurnService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateTurnCommandHandler implements ICommandHandler<CreateTurnCommand> {

    private final ITurnService turnService;
    private final IBusinessService businessService;
    private final IResourceService resourceService;
    private final IServiceService serviceService;

    public CreateTurnCommandHandler(ITurnService turnService, IBusinessService businessService, IResourceService resourceService, IServiceService serviceService) {
        this.turnService = turnService;
        this.businessService = businessService;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(CreateTurnCommand command) {
        BusinessDto businessDto = businessService.findById(command.getBusiness());

        ResourceDto resourceDto = command.getDoctor() != null ? resourceService.findById(command.getDoctor()) : null;
        ServiceDto serviceDto = serviceService.findByIds(command.getService());
        UUID id = turnService.create(new TurnDto(
                command.getId(),
                resourceDto,
                serviceDto,
                command.getIdentification(),
                RandomNumberGenerator.generateRandomNumber(0,100),
                command.getPriority(),
                command.getIsPreferential(),
                "0 min",
                ETurnStatus.PENDING,
                businessDto
        ));
        command.setId(id);
    }
}