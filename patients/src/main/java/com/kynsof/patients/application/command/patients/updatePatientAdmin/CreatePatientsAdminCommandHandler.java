package com.kynsof.patients.application.command.patients.updatePatientAdmin;

import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerUpdatePatientsEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreatePatientsAdminCommandHandler implements ICommandHandler<CreatePatientAdminCommand> {

    private final IPatientsService serviceImpl;

    private final ProducerUpdatePatientsEventService patientEventService;

    public CreatePatientsAdminCommandHandler(IPatientsService serviceImpl,
                                             ProducerUpdatePatientsEventService patientEventService
    ) {
        this.serviceImpl = serviceImpl;
        this.patientEventService = patientEventService;
    }

    @Override
    public void handle(CreatePatientAdminCommand command) {
        PatientDto patientDto = serviceImpl.findByIdSimple(command.getId());
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
        this.patientEventService.update(patientDto, null);
    }
}
