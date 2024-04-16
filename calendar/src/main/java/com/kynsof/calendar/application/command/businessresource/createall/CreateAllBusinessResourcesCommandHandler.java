package com.kynsof.calendar.application.command.businessresource.createall;

import com.kynsof.calendar.application.command.businessresource.create.CreateBusinessResourceCommand;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateAllBusinessResourcesCommandHandler implements ICommandHandler<CreateAllBusinessResourcesCommand> {

    private IMediator mediator;

    public CreateAllBusinessResourcesCommandHandler() {
    }

    @Override
    public void handle(CreateAllBusinessResourcesCommand command) {
        this.mediator = command.getMediator();
        for (UUID service : command.getResources()) {
            this.mediator.send(new CreateBusinessResourceCommand(command.getIdBusiness(), service));
        }
    }
}
