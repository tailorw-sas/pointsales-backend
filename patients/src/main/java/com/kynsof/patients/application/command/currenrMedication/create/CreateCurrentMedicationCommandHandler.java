package com.kynsof.patients.application.command.currenrMedication.create;

import com.kynsof.patients.domain.dto.CurrentMerdicationEntityDto;
import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.ICurrentMedicationService;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCurrentMedicationCommandHandler implements ICommandHandler<CreateCurrentMedicationCommand> {

    private final ICurrentMedicationService currentMedicationService;
    private final IMedicalInformationService medicalInformationService;

    public CreateCurrentMedicationCommandHandler(ICurrentMedicationService serviceImpl, IMedicalInformationService patientsService) {
        this.currentMedicationService = serviceImpl;
        this.medicalInformationService = patientsService;
    }

    @Override
    public void handle(CreateCurrentMedicationCommand command) {
        MedicalInformationDto medicalInformationDto = medicalInformationService.findById(command.getMedicalInformationId());
        UUID id =
                currentMedicationService.create(new CurrentMerdicationEntityDto(
                UUID.randomUUID(),
                command.getDosage(),
                command.getName(),
                Status.ACTIVE,
                command.getMedicalInformationId(),
                medicalInformationDto
        ));
        command.setId(id);
    }
}
