package com.kynsof.calendar.application.command.receipt.create;

import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateReceiptCommand implements ICommand {

    private UUID id;
    private Double price;
    private Boolean express;
    private String reasons;
    private UUID user;
    private UUID schedule;
    private UUID service;
    private final String ipAddress;
    private final String userAgent;
    private final EStatusReceipt status;

    public CreateReceiptCommand(Double price, Boolean express, String reasons, UUID user, UUID schedule, UUID service,
                                String ipAddress, String userAgent, EStatusReceipt status) {

        this.price = price;
        this.express = express;
        this.reasons = reasons;
        this.user = user;
        this.schedule = schedule;
        this.service = service;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.status = status;
    }

    public static CreateReceiptCommand fromRequest(CreateReceiptRequest request, String ipAddress, String userAgent) {
        return new CreateReceiptCommand(request.getPrice(), request.getExpress(),
                request.getReasons(), request.getUserId(), request.getScheduleId(), request.getServiceId(),ipAddress ,
                userAgent, request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateReceiptMessage(id);
    }
}
