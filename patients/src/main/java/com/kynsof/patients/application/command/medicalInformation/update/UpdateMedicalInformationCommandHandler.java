package com.kynsof.patients.application.command.medicalInformation.update;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.stereotype.Component;

@Component
public class UpdateMedicalInformationCommandHandler implements ICommandHandler<UpdateMedicalInformationCommand> {

    private final IPatientsService patientsService;
    private final IAdditionalInfoService additionalInfoService;

    public UpdateMedicalInformationCommandHandler(IPatientsService patientsService, IAdditionalInfoService contactInfoService) {
        this.patientsService = patientsService;
        this.additionalInfoService = contactInfoService;
    }

    @Override
    public void handle(UpdateMedicalInformationCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        additionalInfoService.update(new AdditionalInformationDto(
                command.getId(),
                new Patients(patientDto),
                command.getOccupation(),
                command.getMaritalStatus(),
                command.getEmergencyContactName(),
                command.getEmergencyContactPhone(),
                EStatusPatients.ACTIVE
        ));

    }
}
