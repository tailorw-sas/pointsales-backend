package com.kynsof.patients.application.command.patients.update;

import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientsCommandHandler implements ICommandHandler<UpdatePatientsCommand> {

    private final IPatientsService serviceImpl;

    public UpdatePatientsCommandHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdatePatientsCommand command) {
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
