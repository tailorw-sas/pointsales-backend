package com.kynsof.patients.application.command.dependents.update;

import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.infrastructure.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateDependentPatientsCommandHandler implements ICommandHandler<UpdateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;
    private final ProducerSaveFileEventService saveFileEventService;

    public UpdateDependentPatientsCommandHandler(IPatientsService serviceImpl, ProducerSaveFileEventService saveFileEventService) {
        this.serviceImpl = serviceImpl;
        this.saveFileEventService = saveFileEventService;
    }

    @Override
    public void handle(UpdateDependentPatientsCommand command) {

        PatientDto prime = serviceImpl.findByIdSimple(command.getPrimeId());
        String idLogo = null;
        if (command.getPhoto() != null && command.getPhoto().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "patients", command.getName() + ".png", command.getPhoto());
            saveFileEventService.create(fileSave);
            idLogo = photoId.toString();
        }
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
