package com.kynsof.calendar.application.command.schedule.createlote;

import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.infrastructure.service.BusinessServiceImpl;
import com.kynsof.calendar.infrastructure.service.ResourceServiceImpl;
import com.kynsof.calendar.infrastructure.service.ScheduleServiceImpl;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleByLoteCommandHandler implements ICommandHandler<CreateScheduleByLoteCommand> {

    private final IScheduleService scheduleService;
    private final IResourceService serviceResource;
    private final IBusinessService serviceBusiness;

    public CreateScheduleByLoteCommandHandler(ScheduleServiceImpl service, ResourceServiceImpl serviceResource, BusinessServiceImpl serviceBusiness) {
        this.scheduleService = service;
        this.serviceResource = serviceResource;
        this.serviceBusiness = serviceBusiness;
    }

    @Override
    public void handle(CreateScheduleByLoteCommand command) {
//        List<ScheduleDto> schedules = new ArrayList<>();
//        List<LocalDate> dates = this.scheduleService.getBusinessDays(command.getStartDate(), command.getEndDate());
//        BusinessDto _business = this.serviceBusiness.findById(command.getIdBusiness());
//        for (UUID resource : command.getIdResource()) {
//            ResourceDto _resource = this.serviceResource.findById(resource);
//            for (LocalDate date : dates) {
//                for (int i = 0; i < command.getSchedules().size(); i++) {
//                    this.scheduleService.validate(_resource, date, command.getSchedules().get(i).getStartTime(), command.getSchedules().get(i).getEndingTime());
//                    ScheduleDto __schedule = new ScheduleDto(
//                            UUID.randomUUID(),
//                            _resource,
//                            _business,
//                            date,
//                            command.getSchedules().get(i).getStartTime(),
//                            command.getSchedules().get(i).getEndingTime(), 1);
//                    __schedule.setStatus(EStatusSchedule.ACTIVE);
//                    schedules.add(__schedule);
//                }
//            }
//        }
//        this.scheduleService.createAll(schedules);
    }
}
