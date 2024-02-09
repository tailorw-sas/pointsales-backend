package com.kynsof.patients.application.command.dependents.update;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

@Component
public class UpdateDependentPatientsCommandHandler implements ICommandHandler<UpdateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;

    public UpdateDependentPatientsCommandHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateDependentPatientsCommand command) {

        PatientDto prime = serviceImpl.findById(command.getPrimeId());
         serviceImpl.updateDependent(new DependentPatientDto(
                command.getId(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
                Status.ACTIVE,
                prime
        ));
    }
}
