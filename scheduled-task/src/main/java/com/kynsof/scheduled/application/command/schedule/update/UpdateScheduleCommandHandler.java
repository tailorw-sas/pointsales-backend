package com.kynsof.scheduled.application.command.schedule.update;

import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduleCommandHandler  implements ICommandHandler<UpdateScheduleCommand> {

    private final ScheduleServiceImpl serviceImpl;
    private final ResocurceServiceImpl resourceServiceImpl;

    public UpdateScheduleCommandHandler(ScheduleServiceImpl serviceImpl, ResocurceServiceImpl resourceServiceImpl) {
        this.serviceImpl = serviceImpl;
        this.resourceServiceImpl = resourceServiceImpl;
    }

    @Override
    public void handle(UpdateScheduleCommand command) {
       ResourceDto resource = resourceServiceImpl.findById(command.getIdResource());
       serviceImpl.update(new ScheduleDto(command.getId(), resource, command.getDate(), command.getStartTime(), command.getEndingTime(), 1, command.getStatus()));
    }
}