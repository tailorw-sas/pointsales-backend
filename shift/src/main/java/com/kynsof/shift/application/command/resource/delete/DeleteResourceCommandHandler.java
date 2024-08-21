package com.kynsof.shift.application.command.resource.delete;

import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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
