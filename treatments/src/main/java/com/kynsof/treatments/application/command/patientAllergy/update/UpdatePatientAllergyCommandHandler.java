package com.kynsof.treatments.application.command.patientAllergy.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.dto.PathologicalHistoryDto;
import com.kynsof.treatments.domain.service.ICie10Service;
import com.kynsof.treatments.domain.service.IPatientAllergyService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientAllergyCommandHandler implements ICommandHandler<UpdatePatientAllergyCommand> {

    private final IPatientAllergyService patientAllergyService;
    private final ICie10Service cie10Service;

    public UpdatePatientAllergyCommandHandler(IPatientsService patientsService, IPatientAllergyService patientAllergyService, ICie10Service cie10Service) {

        this.patientAllergyService = patientAllergyService;
        this.cie10Service = cie10Service;
    }

    @Override
    public void handle(UpdatePatientAllergyCommand command) {
        PathologicalHistoryDto patientAllergyDto = patientAllergyService.findById(command.getId());
        if (command.getCie10() != null) {
            Cie10Dto cie10Dto = this.cie10Service.findByCode(command.getCie10());
            patientAllergyDto.setCie10(cie10Dto);
        }
        patientAllergyDto.setObservations(command.getObservations());
        patientAllergyDto.setStatus(command.getStatus());
        patientAllergyDto.setType(command.getType());
        patientAllergyService.update(patientAllergyDto);
    }
}
