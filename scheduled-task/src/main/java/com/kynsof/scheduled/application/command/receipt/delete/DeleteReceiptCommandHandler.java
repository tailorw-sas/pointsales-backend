package com.kynsof.scheduled.application.command.receipt.delete;

import com.kynsof.scheduled.domain.service.IReceiptService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteReceiptCommandHandler implements ICommandHandler<ReceiptDeleteCommand> {

    private final IReceiptService service;

    public DeleteReceiptCommandHandler(IReceiptService service) {
        this.service = service;
    }

    @Override
    public void handle(ReceiptDeleteCommand command) {

        service.delete(command.getId());
    }

}
