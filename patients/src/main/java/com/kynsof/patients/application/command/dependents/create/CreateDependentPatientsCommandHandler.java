package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.dto.*;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Patients;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.infrastructure.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateDependentPatientsCommandHandler implements ICommandHandler<CreateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;
    private final ProducerSaveFileEventService saveFileEventService;

    public CreateDependentPatientsCommandHandler(IPatientsService serviceImpl, IContactInfoService contactInfoService,
                                                 IGeographicLocationService geographicLocationService, ProducerSaveFileEventService saveFileEventService) {
        this.serviceImpl = serviceImpl;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
        this.saveFileEventService = saveFileEventService;
    }

    @Override
    public void handle(CreateDependentPatientsCommand command) {

        PatientDto prime = serviceImpl.findByIdSimple(command.getPrimeId());
        UUID idLogo = UUID.randomUUID();
        UUID id = serviceImpl.createDependent(new DependentPatientDto(
                UUID.randomUUID(),
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
                idLogo.toString(),
                command.getDisabilityType(),
                command.getGestationTime()
        ));
        PatientDto patientDto = serviceImpl.findByIdSimple(id);
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(
                command.getCreateContactInfoRequest().getGeographicLocationId());
        UUID idContactId = contactInfoService.create(new ContactInfoDto(
                UUID.randomUUID(),
                new Patients(patientDto),
                command.getCreateContactInfoRequest().getEmail(),
                command.getCreateContactInfoRequest().getEmail(),
                command.getCreateContactInfoRequest().getAddress(),
                command.getCreateContactInfoRequest().getBirthdayDate(),
                Status.ACTIVE,
                geographicLocationDto
        ));
        command.setId(id);
        FileKafka fileSave = new FileKafka(idLogo, "patients", command.getName()+".png", command.getPhoto());
        saveFileEventService.create(fileSave);
    }
}
