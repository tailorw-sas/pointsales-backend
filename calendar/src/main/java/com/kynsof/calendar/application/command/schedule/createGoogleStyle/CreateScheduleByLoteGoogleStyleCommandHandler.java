package com.kynsof.calendar.application.command.schedule.createGoogleStyle;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.infrastructure.entity.Schedule;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateScheduleByLoteGoogleStyleCommandHandler implements ICommandHandler<CreateScheduleByLoteGoogleStyleCommand> {

    private final IBusinessService businessService;
    private final IResourceService resourceService;
    private final IServiceService serviceService;
    private final IScheduleService scheduleService;

    public CreateScheduleByLoteGoogleStyleCommandHandler(IBusinessService businessService,
            IResourceService resourceService,
            IServiceService serviceService,
            IScheduleService scheduleService) {
        this.businessService = businessService;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
        this.scheduleService = scheduleService;
    }

    @Override
    public void handle(CreateScheduleByLoteGoogleStyleCommand command) {

        BusinessDto business = this.businessService.findById(command.getBusiness());
        ResourceDto resource = this.resourceService.findById(command.getResource());
        ServiceDto service = this.serviceService.findByIds(command.getService());

        List<ScheduleDto> schedules = new ArrayList<>();

        for (ScheduleRequest day : command.getDays()) {
            LocalDate date = day.getStart().toLocalDate();
            LocalTime start = day.getStart().toLocalTime();
            LocalTime end = day.getEnd().toLocalTime();

            List<Schedule> validate = this.scheduleService.findOverlappingSchedules(resource.getId(), date, start, end);
            if (validate.isEmpty()) {
                schedules.add(new ScheduleDto(
                        UUID.randomUUID(),
                        resource,
                        business,
                        date,
                        start,
                        end,
                        day.getStock(),
                        1,
                        EStatusSchedule.AVAILABLE,
                        service
                ));
            } else {
                throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SCHEDULED_TASK_ALREADY_EXISTS, new ErrorField("id", "A scheduled task for this service already exists.")));
            }
        }

        this.scheduleService.createAll(schedules);
    }

}
