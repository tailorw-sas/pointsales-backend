package com.kynsof.treatments.application.command.patientVaccine.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.PatientVaccineDto;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.service.IPatientVaccineService;
import com.kynsof.treatments.domain.service.IPatientsService;
import com.kynsof.treatments.domain.service.IVaccineService;
import com.kynsof.treatments.infrastructure.entity.PatientVaccine;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UpdatePatientVaccineCommandHandler implements ICommandHandler<UpdatePatientVaccineCommand> {

    private final IPatientVaccineService patientVaccineService;
    private final IPatientsService patientsService;
    private final IVaccineService vaccineService;

    public UpdatePatientVaccineCommandHandler(IPatientVaccineService patientVaccineService, IPatientsService patientsService, IVaccineService vaccineService) {
        this.patientVaccineService = patientVaccineService;
        this.patientsService = patientsService;
        this.vaccineService = vaccineService;
    }

    @Override
    public void handle(UpdatePatientVaccineCommand command) {

        PatientDto patientDto = patientsService.findById(command.getPatientId());
        VaccineDto vaccineDto = vaccineService.findById(command.getVaccineId());
        PatientVaccineDto patientVaccineDto = patientVaccineService.findById(command.getId());
        patientVaccineDto.setVaccinationDate(new Date());
        patientVaccineDto.setVaccine(vaccineDto);
        patientVaccineDto.setPatient(patientDto);
        patientVaccineDto.setStatus(command.getStatus());
        UUID id = patientVaccineService.update(new PatientVaccine(patientVaccineDto));

    }
}
