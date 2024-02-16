package com.kynsof.calendar.application.command.schedule.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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
