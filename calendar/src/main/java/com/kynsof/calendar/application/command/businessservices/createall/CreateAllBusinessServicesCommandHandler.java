package com.kynsof.calendar.application.command.businessservices.createall;

import com.kynsof.calendar.application.command.businessservices.create.CreateBusinessServicesCommand;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateAllBusinessServicesCommandHandler implements ICommandHandler<CreateAllBusinessServicesCommand> {

    private IMediator mediator;

    public CreateAllBusinessServicesCommandHandler() {
    }

    @Override
    public void handle(CreateAllBusinessServicesCommand command) {
        this.mediator = command.getMediator();
        for (UUID service : command.getServices()) {
            this.mediator.send(new CreateBusinessServicesCommand(command.getIdBusiness(), service));
        }
    }
}
