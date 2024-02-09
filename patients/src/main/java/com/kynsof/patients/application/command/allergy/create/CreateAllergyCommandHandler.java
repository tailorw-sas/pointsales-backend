package com.kynsof.patients.application.command.allergy.create;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.*;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IAllergyService;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateAllergyCommandHandler implements ICommandHandler<CreateAllergyCommand> {

    private final IAllergyService allergyService;
    private final IMedicalInformationService medicalInformationService;

    public CreateAllergyCommandHandler(IAllergyService serviceImpl, IMedicalInformationService patientsService) {
        this.allergyService = serviceImpl;
        this.medicalInformationService = patientsService;
    }

    @Override
    public void handle(CreateAllergyCommand command) {
        MedicalInformationDto medicalInformationDto = medicalInformationService.findById(command.getMedicalInformationId());
        UUID id = allergyService.create(new AllergyEntityDto(
                UUID.randomUUID(),
                command.getCode(),
                command.getName(),
                Status.ACTIVE,
                command.getMedicalInformationId(),
                medicalInformationDto
        ));
        command.setId(id);
    }
}
