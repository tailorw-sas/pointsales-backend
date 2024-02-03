package com.kynsof.scheduled.application.command.business.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeleteBusinessCommandHandler implements ICommandHandler<BusinessDeleteCommand> {

    private final BusinessServiceImpl serviceImpl;

    public DeleteBusinessCommandHandler(BusinessServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(BusinessDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
