package com.kynsof.scheduled.application.command.service.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ServiceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeleteServiceCommandHandler implements ICommandHandler<ServiceDeleteCommand> {

    private final ServiceServiceImpl serviceImpl;

    public DeleteServiceCommandHandler(ServiceServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(ServiceDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
