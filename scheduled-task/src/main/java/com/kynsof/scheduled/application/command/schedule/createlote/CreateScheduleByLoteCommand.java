package com.kynsof.scheduled.application.command.schedule.createlote;

import com.kynsof.scheduled.application.command.schedule.create.CreateScheduleMessage;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateScheduleByLoteCommand implements ICommand {

    private List<UUID> idResource;
    private UUID idBusiness;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CreateScheduleRequest> schedules;

    public CreateScheduleByLoteCommand(List<UUID> idResource, UUID idBusiness, LocalDate startDate, LocalDate endDate, List<CreateScheduleRequest> schedules) {
        this.idResource = idResource;
        this.idBusiness = idBusiness;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedules = schedules;
    }

    public static CreateScheduleByLoteCommand fromRequest(CreateScheduleByLoteRequest request) {
        return new CreateScheduleByLoteCommand(request.getIdResource(), request.getIdBusiness(), request.getStartDate(), request.getEndDate(), request.getSchedules());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleMessage(UUID.randomUUID());
    }
}
