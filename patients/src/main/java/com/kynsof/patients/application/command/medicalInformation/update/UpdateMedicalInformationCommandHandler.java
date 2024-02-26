package com.kynsof.patients.application.command.medicalInformation.update;

import com.kynsof.patients.domain.dto.MedicalInformationUpdateDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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
                Status.ACTIVE
        ));

    }
}
