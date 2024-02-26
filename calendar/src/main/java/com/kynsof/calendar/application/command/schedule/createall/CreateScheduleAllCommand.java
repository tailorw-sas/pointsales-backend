package com.kynsof.calendar.application.command.schedule.createall;

import com.kynsof.calendar.application.command.schedule.create.CreateScheduleMessage;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
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
