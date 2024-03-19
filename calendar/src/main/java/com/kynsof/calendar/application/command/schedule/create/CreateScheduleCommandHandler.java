package com.kynsof.calendar.application.command.schedule.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateScheduleCommandHandler implements ICommandHandler<CreateScheduleCommand> {

    private final IScheduleService service;
    private final IResourceService serviceResource;
    private final IBusinessService serviceBusiness;
    private final IServiceService serviceService;

    public CreateScheduleCommandHandler(IScheduleService service, IResourceService serviceResource, IBusinessService serviceBusiness, IServiceService serviceService) {
        this.service = service;
        this.serviceResource = serviceResource;
        this.serviceBusiness = serviceBusiness;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(CreateScheduleCommand command) {
        ResourceDto _resource = this.serviceResource.findById(command.getResourceId());
        BusinessDto _business = this.serviceBusiness.findById(command.getBusinessId());
        ServiceDto _service = this.serviceService.findById(command.getServiceId());
        UUID id = UUID.randomUUID();
        service.create(new ScheduleDto(id, _resource, _business, command.getDate(), command.getStartTime(), command.getEndingTime(),
                command.getStock(), command.getStock(), EStatusSchedule.ACTIVE,_service));
        command.setId(id);
    }
}
