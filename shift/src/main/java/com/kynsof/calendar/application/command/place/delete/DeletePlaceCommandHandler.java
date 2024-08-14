package com.kynsof.calendar.application.command.place.delete;

import com.kynsof.calendar.domain.service.IPlaceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeletePlaceCommandHandler implements ICommandHandler<DeletePlaceCommand> {

    private final IPlaceService service;

    public DeletePlaceCommandHandler(IPlaceService service) {
        this.service = service;
    }

    @Override
    public void handle(DeletePlaceCommand command) {

        service.delete(command.getId());
    }

}
