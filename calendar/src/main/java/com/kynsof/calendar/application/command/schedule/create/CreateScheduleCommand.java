package com.kynsof.calendar.application.command.schedule.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class CreateScheduleCommand implements ICommand {

    private UUID id;
    private UUID resourceId;
    private UUID businessId;
    private UUID serviceId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;

    public CreateScheduleCommand(UUID idResource, UUID idBusiness, LocalDate date, LocalTime startTime, LocalTime endingTime,
                                 int stock, UUID idService) {
        this.id = UUID.randomUUID();
        this.resourceId = idResource;
        this.businessId = idBusiness;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.stock = stock;
        this.serviceId = idService;
    }

    public static CreateScheduleCommand fromRequest(CreateScheduleRequest request) {
        return new CreateScheduleCommand(request.getResource(), request.getBusiness(), request.getDate(), request.getStartTime(), request.getEndingTime(), request.getStock(),
                request.getService());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleMessage(id);
    }
}
