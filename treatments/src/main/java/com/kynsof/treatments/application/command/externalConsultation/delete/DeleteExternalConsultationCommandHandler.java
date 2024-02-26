package com.kynsof.treatments.application.command.externalConsultation.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.infrastructure.service.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeleteExternalConsultationCommandHandler implements ICommandHandler<PatientsExternalConsultationCommand> {

    private final PatientsServiceImpl serviceImpl;

    public DeleteExternalConsultationCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(PatientsExternalConsultationCommand command) {

        serviceImpl.delete(command.getId());
    }

}
