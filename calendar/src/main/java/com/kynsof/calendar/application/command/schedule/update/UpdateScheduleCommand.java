package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class UpdateScheduleCommand implements ICommand {

    private UUID id;
    private UUID resource;
    private UUID business;
    private UUID service;
    private UUID user;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private EStatusSchedule status;
    private int stock;
    private final IMediator mediator;
    private final String ipAddress;
    private final String userAgent;

    public UpdateScheduleCommand(UUID id, UUID idResource, LocalDate date, LocalTime startTime,
            LocalTime endingTime, EStatusSchedule status, int stock, UUID business,
            UUID service, UUID user, IMediator mediator, String ipAddress, String userAgent) {
        this.id = id;
        this.resource = idResource;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.status = status;
        this.stock = stock;
        this.business = business;
        this.service = service;
        this.user = user;
        this.mediator = mediator;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    public static UpdateScheduleCommand fromRequest(UUID id, ScheduleUpdateRequest request, IMediator mediator, String ipAddress, String userAgent) {
        return new UpdateScheduleCommand(id, request.getResource(), request.getDate(), request.getStartTime(),
                request.getEndingTime(), request.getStatus(), request.getStock(), request.getBusiness(),
                request.getService(), request.getUser(), mediator, ipAddress, userAgent);
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateScheduleMessage(id);
    }
}
