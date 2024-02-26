package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduleCommandHandler  implements ICommandHandler<UpdateScheduleCommand> {


    private final IScheduleService service;
    private final IResourceService serviceResource;
    
    public UpdateScheduleCommandHandler(IScheduleService service, IResourceService serviceResource) {
        this.service = service;
        this.serviceResource = serviceResource;
    }

    @Override
    public void handle(UpdateScheduleCommand command) {
       ResourceDto resource = serviceResource.findById(command.getIdResource());
       service.update(new ScheduleDto(command.getId(), resource, command.getDate(), command.getStartTime(), command.getEndingTime(), 1, command.getStatus()));
    }
}