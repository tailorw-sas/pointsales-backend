package com.kynsof.patients.application.command.patients.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerCreatePatientsEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePatientsCommandHandler implements ICommandHandler<CreatePatientsCommand> {

    private final IPatientsService serviceImpl;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;
    private final ProducerSaveFileEventService saveFileEventService;

    private final ProducerCreatePatientsEventService patientEventService;

    public CreatePatientsCommandHandler(IPatientsService serviceImpl, IContactInfoService contactInfoService,
                                        IGeographicLocationService geographicLocationService,
                                        ProducerSaveFileEventService saveFileEventService, ProducerCreatePatientsEventService patientEventService
    ) {
        this.serviceImpl = serviceImpl;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
        this.saveFileEventService = saveFileEventService;
        this.patientEventService = patientEventService;
    }

    @Override
    public void handle(CreatePatientsCommand command) {
        String idLogo = null;
        if (command.getPhoto() != null && command.getPhoto().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "patients", command.getName() + ".png", command.getPhoto());
            saveFileEventService.create(fileSave);
            idLogo = photoId.toString();
        }
        UUID id = serviceImpl.create(new PatientDto(
                UUID.randomUUID(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
                Status.ACTIVE,
                command.getWeight(),
                command.getHeight(),
                command.getHasDisability(),
                command.getIsPregnant(),
                idLogo,
                command.getDisabilityType(),
                command.getGestationTime()
        ));
        command.setId(id);
        PatientDto patientDto = serviceImpl.findByIdSimple(id);
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(
                command.getCreateContactInfoRequest().getGeographicLocationId());
        UUID idContactId = contactInfoService.create(new ContactInfoDto(
                UUID.randomUUID(),
                patientDto,
                command.getCreateContactInfoRequest().getEmail(),
                command.getCreateContactInfoRequest().getTelephone(),
                command.getCreateContactInfoRequest().getAddress(),
                command.getCreateContactInfoRequest().getBirthdayDate(),
                Status.ACTIVE,
                geographicLocationDto
        ));

        this.patientEventService.create(patientDto);
    }
}
