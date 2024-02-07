package com.kynsof.scheduled.application.command.schedule.create;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleCommandHandler implements ICommandHandler<CreateScheduleCommand> {

    private final ScheduleServiceImpl serviceImpl;
    private final ResocurceServiceImpl serviceResourceImpl;
    private final BusinessServiceImpl serviceBusinessImpl;

    public CreateScheduleCommandHandler(ScheduleServiceImpl serviceImpl, ResocurceServiceImpl serviceResourceImpl, BusinessServiceImpl serviceBusinessImpl) {
        this.serviceImpl = serviceImpl;
        this.serviceResourceImpl = serviceResourceImpl;
        this.serviceBusinessImpl = serviceBusinessImpl;
    }

    @Override
    public void handle(CreateScheduleCommand command) {
        ResourceDto _resource = this.serviceResourceImpl.findById(command.getIdResource());
        BusinessDto _business = this.serviceBusinessImpl.findById(command.getIdBusiness());

        serviceImpl.create(new ScheduleDto(command.getId(), _resource, _business, command.getDate(), command.getStartTime(), command.getEndingTime(), command.getStock()));
    }
}
