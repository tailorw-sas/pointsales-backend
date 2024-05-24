package com.kynsof.patients.application.command.patients.delete;

import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientsCommandHandler implements ICommandHandler<DeletePatientsCommand> {

    private final PatientsServiceImpl serviceImpl;

    public DeletePatientsCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeletePatientsCommand command) {
        PatientDto delete = this.serviceImpl.findByIdSimple(command.getId());

        serviceImpl.delete(delete);
    }

}
