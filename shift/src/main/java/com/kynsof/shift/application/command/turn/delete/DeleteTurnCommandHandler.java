package com.kynsof.shift.application.command.turn.delete;

import com.kynsof.shift.domain.service.ITurnService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteTurnCommandHandler implements ICommandHandler<DeleteTurnCommand> {

    private final ITurnService service;

    public DeleteTurnCommandHandler(ITurnService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteTurnCommand command) {

        service.delete(command.getId());
    }

}
