package com.kynsof.calendar.application.command.businessService.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessServicesCommand implements ICommand {

    private UUID id;
    private UUID business;
    private UUID service;

    public UpdateBusinessServicesCommand(UUID id, UUID business, UUID service) {
        this.id = id;
        this.business = business;
        this.service = service;
    }

    public static UpdateBusinessServicesCommand fromRequest(UpdateBusinessServicesRequest request, UUID id) {
        return new UpdateBusinessServicesCommand(id, request.getBusiness(), request.getService());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBusinessServicesMessage(id);
    }
}
