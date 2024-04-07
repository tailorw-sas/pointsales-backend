package com.kynsof.calendar.application.command.schedule.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateScheduleAllCommand implements ICommand {

    private UUID idResource;
    private UUID idBusiness;
    private UUID serviceId;
    private LocalDate date;
    private List<ScheduleAllRequest> schedules;

    private final IMediator mediator;

    public CreateScheduleAllCommand(
            UUID idResource,
            UUID idBusiness,
            UUID serviceId,
            LocalDate date,
            List<ScheduleAllRequest> schedules,
            IMediator mediator) {
        this.idResource = idResource;
        this.idBusiness = idBusiness;
        this.serviceId = serviceId;
        this.date = date;
        this.schedules = List.copyOf(schedules);
        this.mediator = mediator;
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllScheduleMessage();
    }
}
