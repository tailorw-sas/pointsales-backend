package com.kynsof.calendar.application.command.serviceType.delete;

import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteServiceTypeCommandHandler implements ICommandHandler<DeleteServiceTypeCommand> {

    private final IServiceTypeService service;

    public DeleteServiceTypeCommandHandler(IServiceTypeService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteServiceTypeCommand command) {

        service.delete(command.getId());
    }

}
