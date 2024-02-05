package com.kynsof.scheduled.application.command.schedule.createall;

import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateAllScheduleCommandHandler implements ICommandHandler<CreateAllScheduleCommand> {

    private final ScheduleServiceImpl serviceImpl;
    private final ResocurceServiceImpl serviceResourceImpl;

    public CreateAllScheduleCommandHandler(ScheduleServiceImpl serviceImpl, ResocurceServiceImpl serviceResourceImpl) {
        this.serviceImpl = serviceImpl;
        this.serviceResourceImpl = serviceResourceImpl;
    }

    @Override
    public void handle(CreateAllScheduleCommand command) {
        ResourceDto _resource = this.serviceResourceImpl.findById(command.getIdResource());

        List<ScheduleDto> schedule = new ArrayList<>();
        for (int i = 0; i < command.getSchedules().size(); i++) {
            this.serviceImpl.validate(_resource, command.getDate(), command.getSchedules().get(i).getStartTime(), command.getSchedules().get(i).getEndingTime());
            ScheduleDto __schedule = new ScheduleDto(
                    UUID.randomUUID(),
                    _resource,
                    command.getDate(),
                    command.getSchedules().get(i).getStartTime(),
                    command.getSchedules().get(i).getEndingTime(), 1);
            __schedule.setStatus(EStatusSchedule.ACTIVE);
            schedule.add(__schedule);
        }
        this.serviceImpl.createAll(schedule);

    }
}
