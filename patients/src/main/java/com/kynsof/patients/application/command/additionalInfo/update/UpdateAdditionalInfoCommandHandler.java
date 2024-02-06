package com.kynsof.patients.application.command.additionalInfo.update;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.Status;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.stereotype.Component;

@Component
public class UpdateAdditionalInfoCommandHandler implements ICommandHandler<UpdateAdditionalInfoCommand> {

    private final IPatientsService patientsService;
    private final IAdditionalInfoService additionalInfoService;

    public UpdateAdditionalInfoCommandHandler(IPatientsService patientsService, IAdditionalInfoService contactInfoService) {
        this.patientsService = patientsService;
        this.additionalInfoService = contactInfoService;
    }

    @Override
    public void handle(UpdateAdditionalInfoCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        additionalInfoService.update(new AdditionalInformationDto(
                command.getId(),
                new Patients(patientDto),
                command.getOccupation(),
                command.getMaritalStatus(),
                command.getEmergencyContactName(),
                command.getEmergencyContactPhone(),
                Status.ACTIVE
        ));

    }
}
