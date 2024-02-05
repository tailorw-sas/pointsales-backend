package com.kynsof.scheduled.application.command.schedule.create;

import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleCommandHandler implements ICommandHandler<CreateScheduleCommand> {

    private final ScheduleServiceImpl serviceImpl;
    private final ResocurceServiceImpl serviceResourceImpl;

    public CreateScheduleCommandHandler(ScheduleServiceImpl serviceImpl, ResocurceServiceImpl serviceResourceImpl) {
        this.serviceImpl = serviceImpl;
        this.serviceResourceImpl = serviceResourceImpl;
    }

    @Override
    public void handle(CreateScheduleCommand command) {
        ResourceDto _resource = this.serviceResourceImpl.findById(command.getIdResource());

        serviceImpl.create(new ScheduleDto(command.getId(), _resource, command.getDate(), command.getStartTime(), command.getEndingTime(), command.getStock()));
    }
}
