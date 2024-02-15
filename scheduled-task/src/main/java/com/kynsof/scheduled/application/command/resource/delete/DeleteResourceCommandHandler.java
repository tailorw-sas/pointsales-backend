package com.kynsof.scheduled.application.command.resource.delete;

import com.kynsof.scheduled.domain.service.IResourceService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteResourceCommandHandler implements ICommandHandler<ResourceDeleteCommand> {

    private final IResourceService service;

    public DeleteResourceCommandHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public void handle(ResourceDeleteCommand command) {

        service.delete(command.getId());
    }

}
