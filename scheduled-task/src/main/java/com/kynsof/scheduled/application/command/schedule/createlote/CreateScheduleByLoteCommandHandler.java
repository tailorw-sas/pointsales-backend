package com.kynsof.scheduled.application.command.schedule.createlote;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleByLoteCommandHandler implements ICommandHandler<CreateScheduleByLoteCommand> {

    private final ScheduleServiceImpl serviceImpl;
    private final ResocurceServiceImpl serviceResourceImpl;
    private final BusinessServiceImpl serviceBusinessImpl;

    public CreateScheduleByLoteCommandHandler(ScheduleServiceImpl serviceImpl, ResocurceServiceImpl serviceResourceImpl, BusinessServiceImpl serviceBusinessImpl) {
        this.serviceImpl = serviceImpl;
        this.serviceResourceImpl = serviceResourceImpl;
        this.serviceBusinessImpl = serviceBusinessImpl;
    }

    @Override
    public void handle(CreateScheduleByLoteCommand command) {
        List<ScheduleDto> schedules = new ArrayList<>();
        List<LocalDate> dates = this.serviceImpl.getBusinessDays(command.getStartDate(), command.getEndDate());
        BusinessDto _business = this.serviceBusinessImpl.findById(command.getIdBusiness());
        for (UUID resource : command.getIdResource()) {
            ResourceDto _resource = this.serviceResourceImpl.findById(resource);
            for (LocalDate date : dates) {
                for (int i = 0; i < command.getSchedules().size(); i++) {
                    this.serviceImpl.validate(_resource, date, command.getSchedules().get(i).getStartTime(), command.getSchedules().get(i).getEndingTime());
                    ScheduleDto __schedule = new ScheduleDto(
                            UUID.randomUUID(),
                            _resource,
                            _business,
                            date,
                            command.getSchedules().get(i).getStartTime(),
                            command.getSchedules().get(i).getEndingTime(), 1);
                    __schedule.setStatus(EStatusSchedule.ACTIVE);
                    schedules.add(__schedule);
                }
            }
        }
        this.serviceImpl.createAll(schedules);
    }
}
