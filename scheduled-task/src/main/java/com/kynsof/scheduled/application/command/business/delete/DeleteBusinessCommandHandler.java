package com.kynsof.scheduled.application.command.business.delete;

import com.kynsof.scheduled.domain.service.IBusinessService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteBusinessCommandHandler implements ICommandHandler<BusinessDeleteCommand> {

    private final IBusinessService service;

    public DeleteBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(BusinessDeleteCommand command) {

        service.delete(command.getId());
    }

}
