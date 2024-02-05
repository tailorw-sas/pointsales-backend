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
public class CreateAllScheduleCommand implements ICommand {

    private UUID idResource;
    private LocalDate date;
    private List<ScheduleRequest> schedules;

    public CreateAllScheduleCommand(UUID idResource, LocalDate date, List<ScheduleRequest> schedules) {
        this.idResource = idResource;
        this.date = date;
        this.schedules = schedules;
    }

    public static CreateAllScheduleCommand fromRequest(ScheduleCreateAllRequest request) {
        return new CreateAllScheduleCommand(request.getIdResource(), request.getDate(), request.getSchedules());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleMessage(idResource);
    }
}
