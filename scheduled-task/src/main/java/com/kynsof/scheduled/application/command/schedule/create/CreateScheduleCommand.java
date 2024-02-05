package com.kynsof.scheduled.application.command.schedule.create;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateScheduleCommand implements ICommand {

    private UUID id;
    private UUID idResource;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;

    public CreateScheduleCommand(UUID idResource, LocalDate date, LocalTime startTime, LocalTime endingTime, int stock) {
        this.id = UUID.randomUUID();
        this.idResource = idResource;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.stock = stock;
    }

    public static CreateScheduleCommand fromRequest(CreateScheduleRequest request) {
        return new CreateScheduleCommand(request.getIdResource(), request.getDate(), request.getStartTime(), request.getEndingTime(), request.getStock());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleMessage(id);
    }
}
