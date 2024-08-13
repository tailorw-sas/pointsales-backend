package com.kynsof.calendar.application.command.block.delete;

import com.kynsof.calendar.domain.service.IBlockService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteBlockCommandHandler implements ICommandHandler<DeleteBlockCommand> {

    private final IBlockService service;

    public DeleteBlockCommandHandler(IBlockService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteBlockCommand command) {

        service.delete(command.getId());
    }

}
