package com.kynsof.calendar.application.command.receipt.createReceiptScheduled;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class CreateScheduleReceiptCommand implements ICommand {

    private UUID id;
    private UUID resource;
    private UUID businessId;
    private UUID serviceId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;
    private final String reason;
    private final UUID user;
    private final String ipAddress;
    private final String userAgent;

    public CreateScheduleReceiptCommand(UUID idResource, UUID idBusiness, LocalDate date, LocalTime startTime, LocalTime endingTime,
                                        int stock, UUID idService, String reason, UUID user, String ipAddress, String userAgent) {
        this.reason = reason;
        this.user = user;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.id = UUID.randomUUID();
        this.resource = idResource;
        this.businessId = idBusiness;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.stock = stock;
        this.serviceId = idService;
    }

    public static CreateScheduleReceiptCommand fromRequest(CreateScheduleReceiptRequest request, String ipAddress,
                                                           String userAgent) {
        return new CreateScheduleReceiptCommand(request.getResource(), request.getBusiness(), request.getDate(),
                request.getStartTime(), request.getEndingTime(), request.getStock(),
                request.getService(), request.getReason(), request.getUser(),ipAddress ,
                userAgent);
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleReceiptMessage(id);
    }
}
