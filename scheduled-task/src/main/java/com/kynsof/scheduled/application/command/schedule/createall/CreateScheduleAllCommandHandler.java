package com.kynsof.scheduled.application.command.schedule.createall;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleAllCommandHandler implements ICommandHandler<CreateScheduleAllCommand> {

    private final ScheduleServiceImpl serviceImpl;
    private final ResocurceServiceImpl serviceResourceImpl;
    private final BusinessServiceImpl serviceBusinessImpl;

    public CreateScheduleAllCommandHandler(ScheduleServiceImpl serviceImpl, ResocurceServiceImpl serviceResourceImpl, BusinessServiceImpl serviceBusinessImpl) {
        this.serviceImpl = serviceImpl;
        this.serviceResourceImpl = serviceResourceImpl;
        this.serviceBusinessImpl = serviceBusinessImpl;
    }

    @Override
    public void handle(CreateScheduleAllCommand command) {
        ResourceDto _resource = this.serviceResourceImpl.findById(command.getIdResource());
        BusinessDto _business = this.serviceBusinessImpl.findById(command.getIdBusiness());

        List<ScheduleDto> schedule = new ArrayList<>();
        for (int i = 0; i < command.getSchedules().size(); i++) {
            this.serviceImpl.validate(_resource, command.getDate(), command.getSchedules().get(i).getStartTime(), command.getSchedules().get(i).getEndingTime());
            ScheduleDto __schedule = new ScheduleDto(
                    UUID.randomUUID(),
                    _resource,
                    _business,
                    command.getDate(),
                    command.getSchedules().get(i).getStartTime(),
                    command.getSchedules().get(i).getEndingTime(), 1);
            __schedule.setStatus(EStatusSchedule.ACTIVE);
            schedule.add(__schedule);
        }
        this.serviceImpl.createAll(schedule);

    }
}
