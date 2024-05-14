package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduleCommandHandler  implements ICommandHandler<UpdateScheduleCommand> {


    private final IScheduleService scheduleService;
    private final IResourceService serviceResource;
    
    public UpdateScheduleCommandHandler(IScheduleService service, IResourceService serviceResource) {
        this.scheduleService = service;
        this.serviceResource = serviceResource;
    }

    @Override
    public void handle(UpdateScheduleCommand command) {
       ResourceDto _resource = serviceResource.findById(command.getIdResource());
       ScheduleDto _scheduled = scheduleService.findById(command.getId());
       _scheduled.setStock(command.getStock());
       _scheduled.setDate(command.getDate());
       _scheduled.setStartTime(command.getStartTime());
       _scheduled.setEndingTime(command.getEndingTime());
       scheduleService.update(_scheduled);
    }
}