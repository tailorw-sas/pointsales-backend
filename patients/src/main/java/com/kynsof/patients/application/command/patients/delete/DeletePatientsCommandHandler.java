package com.kynsof.patients.application.command.patients.delete;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientsCommandHandler implements ICommandHandler<PatientsDeleteCommand> {

    private final PatientsServiceImpl serviceImpl;

    public DeletePatientsCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(PatientsDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
