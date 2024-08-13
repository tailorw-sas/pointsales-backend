package com.kynsof.calendar.application.command.tunerSpecialties.delete;

import com.kynsof.calendar.domain.service.ITurnerSpecialtiesService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteTurnerSpecialtiesCommandHandler implements ICommandHandler<DeleteTurnerSpecialtiesCommand> {

    private final ITurnerSpecialtiesService service;

    public DeleteTurnerSpecialtiesCommandHandler(ITurnerSpecialtiesService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteTurnerSpecialtiesCommand command) {

        service.delete(command.getId());
    }

}
