package com.kynsof.shift.infrastructure.excel.event;

import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesBulkCommand;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateTurnerSpecialtiesEventHandler implements ApplicationListener<CreateTurnerSpecialtiesEvent> {
    private final IMediator mediator;

    public CreateTurnerSpecialtiesEventHandler(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onApplicationEvent(CreateTurnerSpecialtiesEvent event) {
        List<CreateTurnerSpecialtiesCommand> command = event.getCommand();
        CreateTurnerSpecialtiesBulkCommand commandBulk = new CreateTurnerSpecialtiesBulkCommand(command);
        mediator.send(commandBulk);
    }
}
