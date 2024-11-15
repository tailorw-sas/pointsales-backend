package com.kynsof.treatments.application.command.patientAllergy.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.dto.PatientAllergyDto;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.service.ICie10Service;
import com.kynsof.treatments.domain.service.IPatientAllergyService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePatientAllergyCommandHandler implements ICommandHandler<CreatePatientAllergyCommand> {

    private final IPatientsService patientsService;
    private final IPatientAllergyService patientAllergyService;
    private final ICie10Service cie10Service;

    public CreatePatientAllergyCommandHandler(IPatientsService patientsService, IPatientAllergyService patientAllergyService, ICie10Service cie10Service) {
        this.patientsService = patientsService;
        this.patientAllergyService = patientAllergyService;
        this.cie10Service = cie10Service;
    }

    @Override
    public void handle(CreatePatientAllergyCommand command) {
        PatientDto patientDto = this.patientsService.findById(command.getPatientId());
        Cie10Dto cie10Dto = this.cie10Service.findByCode(command.getCie10());

        UUID id = patientAllergyService.create(new PatientAllergyDto(
                UUID.randomUUID(),
                patientDto,
                cie10Dto,
                command.getSeverity(),
                command.getReaction(),
                command.getStatus()
        ));
        command.setAllergyId(id);
    }
}
