package com.kynsof.calendar.application.command.receipt.create;

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
    public CreateReceiptCommand(Double price, Boolean express, String reasons, UUID user, UUID schedule, UUID service) {
        this.id = UUID.randomUUID();
        this.price = price;
        this.express = express;
        this.reasons = reasons;
        this.user = user;
        this.schedule = schedule;
        this.service = service;
    }

    public static CreateReceiptCommand fromRequest(CreateReceiptRequest request) {
        return new CreateReceiptCommand(request.getPrice(), request.getExpress(), request.getReasons(), request.getUser(), request.getSchedule(), request.getService());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateReceiptMessage(id);
    }
}
