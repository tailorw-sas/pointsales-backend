package com.kynsof.calendar.application.command.schedule.createlote;

import com.kynsof.calendar.application.command.schedule.createall.ScheduleAllRequest;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateScheduleByLoteCommand implements ICommand {
    private UUID idResource;
    private UUID idBusiness;
    private UUID serviceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ScheduleAllRequest> schedules;
    List<DayOfWeek> daysToExclude;

    private final IMediator mediator;

    public CreateScheduleByLoteCommand(
            UUID idResource, 
            UUID idBusiness, 
            UUID serviceId, 
            LocalDate startDate, 
            LocalDate endDate, 
            List<ScheduleAllRequest> schedules,
            List<DayOfWeek> daysToExclude,
            IMediator mediator) {
        this.idResource = idResource;
        this.idBusiness = idBusiness;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedules = List.copyOf(schedules);
        this.daysToExclude = List.copyOf(daysToExclude);
        this.mediator = mediator;
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleByLoteMessage();
    }
}
