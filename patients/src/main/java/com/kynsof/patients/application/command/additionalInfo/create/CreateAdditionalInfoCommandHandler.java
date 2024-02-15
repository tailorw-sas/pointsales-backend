package com.kynsof.patients.application.command.additionalInfo.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateAdditionalInfoCommandHandler implements ICommandHandler<CreateAdditionalInfoCommand> {

    private final IAdditionalInfoService additionalInfoService;
    private final IPatientsService patientsService;

    public CreateAdditionalInfoCommandHandler(IAdditionalInfoService serviceImpl, IPatientsService patientsService) {
        this.additionalInfoService = serviceImpl;
        this.patientsService = patientsService;
    }

    @Override
    public void handle(CreateAdditionalInfoCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        UUID id = additionalInfoService.create(new AdditionalInformationDto(
                UUID.randomUUID(),
                new Patients(patientDto),
                command.getOccupation(),
                command.getMaritalStatus(),
                command.getEmergencyContactName(),
                command.getEmergencyContactPhone(),
                Status.ACTIVE
        ));
        command.setId(id);
    }
}
