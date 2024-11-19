package com.kynsof.calendar.application.command.businessService.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessServicesCommand implements ICommand {

    private boolean result;
    private final UUID businessServiceId;
    private final Double price;

    public UpdateBusinessServicesCommand(UUID businessServiceId, Double price) {

        this.businessServiceId = businessServiceId;
        this.price = price;
    }

    public static UpdateBusinessServicesCommand fromRequest(UpdateBusinessServicesRequest request) {
        return new UpdateBusinessServicesCommand(request.getBusinessServiceId(), request.getPrice());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBusinessServicesMessage(true);
    }
}
