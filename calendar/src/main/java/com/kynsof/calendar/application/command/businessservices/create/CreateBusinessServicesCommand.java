package com.kynsof.calendar.application.command.businessservices.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessServicesCommand implements ICommand {

    private UUID id;
    private UUID business;
    private UUID service;

    public CreateBusinessServicesCommand(UUID business, UUID service) {
        this.id = UUID.randomUUID();
        this.business = business;
        this.service = service;
    }

    public static CreateBusinessServicesCommand fromRequest(CreateBusinessServicesRequest request) {
        return new CreateBusinessServicesCommand(request.getBusiness(), request.getService());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateBusinessServicesMessage(id);
    }
}
