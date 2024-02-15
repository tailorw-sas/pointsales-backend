package com.kynsof.patients.application.command.patients.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePatientsCommandHandler implements ICommandHandler<CreatePatientsCommand> {

    private final IPatientsService serviceImpl;

    public CreatePatientsCommandHandler(IPatientsService serviceImpl) {
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
                Status.ACTIVE
        ));
        command.setId(id);
    }
}
