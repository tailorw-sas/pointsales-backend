package com.kynsof.patients.application.command.dependents.createByPatientId;

import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateDependentByPatientIdCommandHandler implements ICommandHandler<CreateDependentByPatientIdCommand> {

    private final IPatientsService serviceImpl;

    public CreateDependentByPatientIdCommandHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateDependentByPatientIdCommand command) {

        PatientDto prime = serviceImpl.findById(command.getPrimeId());
        PatientDto dependent = serviceImpl.findById(command.getPatientId());

        serviceImpl.updateDependent(new DependentPatientDto(
                dependent.getId(),
                dependent.getIdentification(),
                dependent.getName(),
                dependent.getLastName(),
                dependent.getGender(),
                Status.ACTIVE,
                prime,
                dependent.getWeight(),
                dependent.getHeight(),
                dependent.getHasDisability(),
                dependent.getIsPregnant(),
                command.getFamilyRelationship(),
                dependent.getPhoto()
        ));
        command.setId(dependent.getId());
    }
}
