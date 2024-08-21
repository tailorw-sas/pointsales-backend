package com.kynsof.shift.application.command.businessresource.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAllBusinessResourcesCommand implements ICommand {

    private boolean result;
    private UUID idBusiness;
    private List<UUID> resources;

    private final IMediator mediator;

    public CreateAllBusinessResourcesCommand(UUID idBusiness, List<UUID> resources, IMediator mediator) {
        this.idBusiness = idBusiness;
        this.resources = List.copyOf(resources);
        this.mediator = mediator;
    }

    public static CreateAllBusinessResourcesCommand fromRequest(CreateAllBusinessResourcesRequest request, IMediator mediator) {
        return new CreateAllBusinessResourcesCommand(
                request.getIdBusiness(), 
                request.getResources(),
                mediator
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllBusinessResourcesMessage(result);
    }
}
