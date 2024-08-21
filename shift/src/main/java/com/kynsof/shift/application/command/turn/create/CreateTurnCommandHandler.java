package com.kynsof.shift.application.command.turn.create;

import com.kynsof.shift.infrastructure.service.TurnCreationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateTurnCommandHandler implements ICommandHandler<CreateTurnCommand> {

    private final TurnCreationService turnCreationService;

    public CreateTurnCommandHandler(TurnCreationService turnCreationService) {

        this.turnCreationService = turnCreationService;
    }

    @Override
    public void handle(CreateTurnCommand command) {
        CreateTurnRequest request = new CreateTurnRequest();
        request.setDoctor(command.getDoctor());
        request.setService(command.getService());
        request.setBusiness(command.getBusiness());
        request.setIdentification(command.getIdentification());
        UUID id = turnCreationService.createTurn(request);
        command.setId(id);
    }
}