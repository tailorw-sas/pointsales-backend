package com.kynsof.calendar.application.command.businessService.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAllBusinessServicesCommand implements ICommand {

    private boolean result;
    private UUID idBusiness;
    private List<CreateBusinessServicesPriceRequest> services;

    public CreateAllBusinessServicesCommand(UUID idBusiness, List<CreateBusinessServicesPriceRequest> services) {
        this.idBusiness = idBusiness;
        this.services = List.copyOf(services);

    }

    public static CreateAllBusinessServicesCommand fromRequest(CreateAllBusinessServicesRequest request) {
        return new CreateAllBusinessServicesCommand(
                request.getIdBusiness(), 
                request.getServices()

        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllBusinessServicesMessage(result);
    }
}
