package com.kynsof.calendar.application.command.receipt.delete;

import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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
