package com.kynsof.treatments.application.command.vaccine.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.infrastructure.service.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeleteVaccineCommandHandler implements ICommandHandler<VaccineDeleteCommand> {

    private final PatientsServiceImpl serviceImpl;

    public DeleteVaccineCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(VaccineDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
