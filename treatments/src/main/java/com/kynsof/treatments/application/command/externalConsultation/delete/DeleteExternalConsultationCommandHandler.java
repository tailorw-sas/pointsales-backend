package com.kynsof.treatments.application.command.externalConsultation.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class DeleteExternalConsultationCommandHandler implements ICommandHandler<PatientsExternalConsultationCommand> {

    private final IExternalConsultationService serviceImpl;

    public DeleteExternalConsultationCommandHandler(IExternalConsultationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(PatientsExternalConsultationCommand command) {
        ExternalConsultationDto delete = this.serviceImpl.findById(command.getId());
        serviceImpl.delete(delete);
    }

}
