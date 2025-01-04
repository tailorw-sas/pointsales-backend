package com.kynsof.identity.application.command.session.delete;

import com.kynsof.identity.domain.interfaces.service.ISessionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteSessionCommandHandler implements ICommandHandler<DeleteSessionCommand> {

    private final ISessionService service;

    public DeleteSessionCommandHandler(ISessionService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteSessionCommand command) {
        service.delete(command.getId());
    }
}
