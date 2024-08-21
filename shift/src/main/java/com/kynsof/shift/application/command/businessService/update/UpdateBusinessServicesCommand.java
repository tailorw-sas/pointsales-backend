package com.kynsof.shift.application.command.businessService.update;

import com.kynsof.shift.application.command.businessService.create.CreateBusinessServicesPriceRequest;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessServicesCommand implements ICommand {

    private boolean result;
    private final UUID idBusiness;
    private final List<CreateBusinessServicesPriceRequest> services;

    public UpdateBusinessServicesCommand(UUID business,   List<CreateBusinessServicesPriceRequest> services) {


        this.idBusiness = business;
        this.services = services;
    }

    public static UpdateBusinessServicesCommand fromRequest(UpdateBusinessServicesRequest request) {
        return new UpdateBusinessServicesCommand(request.getIdBusiness(), request.getServices());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBusinessServicesMessage(true);
    }
}
