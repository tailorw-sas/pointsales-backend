package com.kynsof.patients.application.command.patients.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientsCommandHandler implements ICommandHandler<DeletePatientsCommand> {

    private final PatientsServiceImpl serviceImpl;

    public DeletePatientsCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeletePatientsCommand command) {

        serviceImpl.delete(command.getId());
    }

}
