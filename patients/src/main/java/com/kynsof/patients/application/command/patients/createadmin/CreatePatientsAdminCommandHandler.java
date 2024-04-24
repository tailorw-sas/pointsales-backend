package com.kynsof.patients.application.command.patients.createadmin;

import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerCreatePatientsEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePatientsAdminCommandHandler implements ICommandHandler<CreatePatientAdminCommand> {

    private final IPatientsService serviceImpl;
    private final ProducerSaveFileEventService saveFileEventService;

    private final ProducerCreatePatientsEventService patientEventService;

    public CreatePatientsAdminCommandHandler(IPatientsService serviceImpl,
                                        ProducerSaveFileEventService saveFileEventService,
                                             ProducerCreatePatientsEventService patientEventService
    ) {
        this.serviceImpl = serviceImpl;
        this.saveFileEventService = saveFileEventService;
        this.patientEventService = patientEventService;
    }

    @Override
    public void handle(CreatePatientAdminCommand command) {
        PatientDto patientDto = serviceImpl.findByIdSimple(command.getId());
        String idLogo = null;
        if (command.getPhoto() != null && command.getPhoto().length > 1) {
            UUID photoId = UUID.randomUUID();
            FileKafka fileSave = new FileKafka(photoId, "patients", patientDto.getName() + ".png", command.getPhoto());
            saveFileEventService.create(fileSave);
            idLogo = photoId.toString();
            patientDto.setPhoto(idLogo);
        }

        patientDto.setGender(command.getGender());
        patientDto.setHeight(command.getHeight());
        patientDto.setWeight(command.getWeight());
        patientDto.setDisabilityType(command.getDisabilityType());
        patientDto.setIdentification(command.getIdentification());
        patientDto.setGestationTime(command.getGestationTime());
        serviceImpl.update(patientDto);
        this.patientEventService.create(patientDto, null);
    }
}
