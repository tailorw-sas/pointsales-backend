package com.kynsof.patients.application.command.patients.updatePatientAdmin;

import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerCreatePatientsEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

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

        patientDto.setPhoto(command.getPhoto());
        patientDto.setGender(command.getGender());
        patientDto.setHeight(command.getHeight());
        patientDto.setWeight(command.getWeight());
        patientDto.setDisabilityType(command.getDisabilityType());
        patientDto.setIdentification(command.getIdentification());
        patientDto.setGestationTime(command.getGestationTime());
        patientDto.setHasDisability(command.getHasDisability());
        patientDto.setIsPregnant(command.getIsPregnant());
        serviceImpl.update(patientDto);
        //this.patientEventService.create(patientDto, null);
    }
}
