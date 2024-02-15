package com.kynsof.scheduled.application.command.schedule.createall;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.domain.service.IBusinessService;
import com.kynsof.scheduled.domain.service.IResourceService;
import com.kynsof.scheduled.domain.service.IScheduleService;
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

    private final IScheduleService service;
    private final IResourceService serviceResource;
    private final IBusinessService serviceBusiness;

    public CreateScheduleAllCommandHandler(ScheduleServiceImpl service, ResocurceServiceImpl serviceResource, BusinessServiceImpl serviceBusiness) {
        this.service = service;
        this.serviceResource = serviceResource;
        this.serviceBusiness = serviceBusiness;
    }

    @Override
    public void handle(CreateScheduleAllCommand command) {
        ResourceDto _resource = this.serviceResource.findById(command.getIdResource());
        BusinessDto _business = this.serviceBusiness.findById(command.getIdBusiness());

        List<ScheduleDto> schedule = new ArrayList<>();
        for (int i = 0; i < command.getSchedules().size(); i++) {
            this.service.validate(_resource, command.getDate(), command.getSchedules().get(i).getStartTime(), command.getSchedules().get(i).getEndingTime());
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
        this.service.createAll(schedule);

    }
}
