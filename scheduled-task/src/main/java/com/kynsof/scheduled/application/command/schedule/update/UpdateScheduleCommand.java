package com.kynsof.scheduled.application.command.schedule.update;

import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateScheduleCommand implements ICommand {

    private UUID id;
    private UUID idResource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private EStatusSchedule status;

    public UpdateScheduleCommand(UUID id, UUID idResource, LocalDate date, LocalTime startTime, LocalTime endingTime, EStatusSchedule status) {
        this.id = id;
        this.idResource = idResource;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.status = status;
    }

    public static UpdateScheduleCommand fromRequest(ScheduleUpdateRequest request) {
        return new UpdateScheduleCommand(request.getId(), request.getIdResource(), request.getDate(), request.getStartTime(), request.getEndingTime(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateScheduleMessage(id);
    }
}
