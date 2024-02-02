package com.kynsof.patients.application.command.patients.update;

import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientsCommandHandler implements ICommandHandler<UpdatePatientsCommand> {

    private final PatientsServiceImpl serviceImpl;

    public UpdatePatientsCommandHandler(PatientsServiceImpl serviceImpl) {
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
               EStatusPatients.ACTIVE
        ));

    }
}
