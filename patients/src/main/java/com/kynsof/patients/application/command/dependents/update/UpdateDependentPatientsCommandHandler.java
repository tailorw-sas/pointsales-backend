package com.kynsof.patients.application.command.dependents.update;

import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateDependentPatientsCommandHandler implements ICommandHandler<UpdateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;


    public UpdateDependentPatientsCommandHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateDependentPatientsCommand command) {

        PatientDto prime = serviceImpl.findByIdSimple(command.getPrimeId());
        String idLogo = null;
//        if (command.getPhoto() != null) {
//          idLogo = sendFileService.sendImage(command.getName(),command.getPhoto());
//        }
        serviceImpl.updateDependent(new DependentPatientDto(
                command.getId(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
                Status.ACTIVE,
                prime,
                command.getWeight(),
                command.getHeight(),
                command.getHasDisability(),
                command.getIsPregnant(),
                command.getFamilyRelationship(),
                idLogo,
                command.getDisabilityType(),
                command.getGestationTime()
        ));

    }
}
