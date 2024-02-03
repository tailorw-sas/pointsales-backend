package com.kynsof.patients.application.command.medicalInformation.update;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.MedicalInformationUpdateDto;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.stereotype.Component;

@Component
public class UpdateMedicalInformationCommandHandler implements ICommandHandler<UpdateMedicalInformationCommand> {

    private final IMedicalInformationService medicalInformationService;

    public UpdateMedicalInformationCommandHandler(IMedicalInformationService contactInfoService) {
        this.medicalInformationService = contactInfoService;
    }

    @Override
    public void handle(UpdateMedicalInformationCommand command) {
        medicalInformationService.update(new MedicalInformationUpdateDto(
                command.getId(),
                command.getBloodType(),
                command.getMedicalHistory(),
                EStatusPatients.ACTIVE
        ));

    }
}
