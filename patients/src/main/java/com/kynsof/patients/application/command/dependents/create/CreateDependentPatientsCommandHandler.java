package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.Status;
import com.kynsof.patients.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateDependentPatientsCommandHandler implements ICommandHandler<CreateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;

    public CreateDependentPatientsCommandHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateDependentPatientsCommand command) {

        PatientDto prime = serviceImpl.findById(command.getPrimeId());
        UUID id = serviceImpl.createDependent(new DependentPatientDto(
                UUID.randomUUID(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
                Status.ACTIVE,
                prime
        ));
        command.setId(id);
    }
}
