package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.treatments.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.Status;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

@Component
public class UpdateExternalConsultationCommandHandler implements ICommandHandler<UpdateExternalConsultationCommand> {

    private final IPatientsService serviceImpl;

    public UpdateExternalConsultationCommandHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateExternalConsultationCommand command) {
      serviceImpl.update(new PatientDto(
                command.getId(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
               Status.ACTIVE
        ));

    }
}
