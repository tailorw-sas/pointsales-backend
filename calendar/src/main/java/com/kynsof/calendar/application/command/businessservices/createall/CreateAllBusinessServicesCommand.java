package com.kynsof.calendar.application.command.businessservices.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateAllBusinessServicesCommand implements ICommand {

    private boolean result;
    private UUID idBusiness;
    private List<UUID> services;

    private final IMediator mediator;

    public CreateAllBusinessServicesCommand(UUID idBusiness, List<UUID> services, IMediator mediator) {
        this.idBusiness = idBusiness;
        this.services = List.copyOf(services);
        this.mediator = mediator;
    }

    public static CreateAllBusinessServicesCommand fromRequest(CreateAllBusinessServicesRequest request, IMediator mediator) {
        return new CreateAllBusinessServicesCommand(
                request.getIdBusiness(), 
                request.getServices(),
                mediator
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllBusinessServicesMessage(result);
    }
}
