package com.kynsof.patients.application.command.medicalInformation.create;

import com.kynsof.patients.domain.dto.AllergyDto;
import com.kynsof.patients.domain.dto.CurrentMedicationDto;
import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateMedicalInformationCommandHandler implements ICommandHandler<CreateMedicalInformationCommand> {

    private final IMedicalInformationService medicalInformationService;
    private final IPatientsService patientsService;

    public CreateMedicalInformationCommandHandler(IMedicalInformationService serviceImpl, IPatientsService patientsService) {
        this.medicalInformationService = serviceImpl;
        this.patientsService = patientsService;
    }

    @Override
    public void handle(CreateMedicalInformationCommand command) {
        PatientDto patientDto = patientsService.findByIdSimple(command.getPatientId());

        List<AllergyDto> allergyDtos = command.getAllergies().stream()
                .map(createAllergyRequest -> new AllergyDto(
                        UUID.randomUUID(),
                        createAllergyRequest.getCode(),
                        createAllergyRequest.getName(), Status.ACTIVE))
                .collect(Collectors.toList());

        List<CurrentMedicationDto> currentMedicationDtos = command.getCurrentMedications().stream()
                .map(currentMedication -> new CurrentMedicationDto(
                        UUID.randomUUID(),
                        currentMedication.getName(),
                        currentMedication.getDescription(), Status.ACTIVE))
                .collect(Collectors.toList());

        UUID id = medicalInformationService.create(new MedicalInformationDto(
                UUID.randomUUID(),
                command.getBloodType(),
                command.getMedicalHistory(),
                command.getPatientId(),
                patientDto,
                allergyDtos,
                currentMedicationDtos,
                Status.ACTIVE
        ));

        command.setId(id);
    }

}
