package com.kynsof.patients.application.command.patients.create;

import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreatePatientsCommandHandler  implements ICommandHandler<CreatePatientsCommand> {

    private final PatientsServiceImpl serviceImpl;

    public CreatePatientsCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreatePatientsCommand command) {
       UUID id = serviceImpl.create(new PatientDto(
                UUID.randomUUID(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
               EStatusPatients.ACTIVE
        ));
       command.setId(id);
    }
}
