package com.kynsof.scheduled.application.command.schedule.create;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.domain.service.IBusinessService;
import com.kynsof.scheduled.domain.service.IResourceService;
import com.kynsof.scheduled.domain.service.IScheduleService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleCommandHandler implements ICommandHandler<CreateScheduleCommand> {

    private final IScheduleService service;
    private final IResourceService serviceResource;
    private final IBusinessService serviceBusiness;

    public CreateScheduleCommandHandler(IScheduleService service, IResourceService serviceResource, IBusinessService serviceBusiness) {
        this.service = service;
        this.serviceResource = serviceResource;
        this.serviceBusiness = serviceBusiness;
    }

    @Override
    public void handle(CreateScheduleCommand command) {
        ResourceDto _resource = this.serviceResource.findById(command.getIdResource());
        BusinessDto _business = this.serviceBusiness.findById(command.getIdBusiness());

        service.create(new ScheduleDto(command.getId(), _resource, _business, command.getDate(), command.getStartTime(), command.getEndingTime(), command.getStock()));
    }
}
