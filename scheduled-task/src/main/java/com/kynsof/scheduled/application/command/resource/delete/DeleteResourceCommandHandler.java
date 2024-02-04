package com.kynsof.scheduled.application.command.resource.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ResocurceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeleteResourceCommandHandler implements ICommandHandler<ResourceDeleteCommand> {

    private final ResocurceServiceImpl serviceImpl;

    public DeleteResourceCommandHandler(ResocurceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(ResourceDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
