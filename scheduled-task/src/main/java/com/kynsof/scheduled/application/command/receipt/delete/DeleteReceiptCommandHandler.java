package com.kynsof.scheduled.application.command.receipt.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ReceiptService;
import org.springframework.stereotype.Component;

@Component
public class DeleteReceiptCommandHandler implements ICommandHandler<ReceiptDeleteCommand> {

    private final ReceiptService serviceImpl;

    public DeleteReceiptCommandHandler(ReceiptService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(ReceiptDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
