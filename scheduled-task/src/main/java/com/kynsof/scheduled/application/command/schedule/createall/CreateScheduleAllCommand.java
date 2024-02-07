package com.kynsof.scheduled.application.command.schedule.createall;

import com.kynsof.scheduled.application.command.schedule.create.*;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateScheduleAllCommand implements ICommand {

    private UUID idResource;
    private UUID idBusiness;
    private LocalDate date;
    private List<ScheduleAllRequest> schedules;

    public CreateScheduleAllCommand(UUID idResource, UUID idBusiness, LocalDate date, List<ScheduleAllRequest> schedules) {
        this.idResource = idResource;
        this.idBusiness = idBusiness;
        this.date = date;
        this.schedules = schedules;
    }

    public static CreateScheduleAllCommand fromRequest(CreateScheduleAllRequest request) {
        return new CreateScheduleAllCommand(request.getIdResource(), request.getIdBusiness(), request.getDate(), request.getSchedules());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleMessage(idResource);
    }
}
