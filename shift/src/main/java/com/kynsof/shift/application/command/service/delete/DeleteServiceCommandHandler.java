package com.kynsof.shift.application.command.service.delete;

import com.kynsof.shift.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteServiceCommandHandler implements ICommandHandler<ServiceDeleteCommand> {

    private final IServiceService service;

    public DeleteServiceCommandHandler(IServiceService service) {
        this.service = service;
    }

    @Override
    public void handle(ServiceDeleteCommand command) {

        service.delete(command.getId());
    }

}
