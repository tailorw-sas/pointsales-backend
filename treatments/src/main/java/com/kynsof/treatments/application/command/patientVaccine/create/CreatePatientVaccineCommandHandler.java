package com.kynsof.treatments.application.command.patientVaccine.create;

import com.kynsof.treatments.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.*;
import com.kynsof.treatments.domain.service.IDoctorService;
import com.kynsof.treatments.domain.service.IPatientVaccineService;
import com.kynsof.treatments.domain.service.IPatientsService;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreatePatientVaccineCommandHandler implements ICommandHandler<CreatePatientVaccineCommand> {

    private final IPatientVaccineService patientVaccineService;
    private final IPatientsService patientsService;
    private final IVaccineService vaccineService;


    public CreatePatientVaccineCommandHandler(IPatientVaccineService externalConsultationService, IPatientsService patientsService,
                                              IVaccineService vaccineService) {
        this.patientVaccineService = externalConsultationService;
        this.patientsService = patientsService;
        this.vaccineService = vaccineService;
    }

    @Override
    public void handle(CreatePatientVaccineCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        VaccineDto vaccineDto = vaccineService.findById(command.getVaccineId());

        UUID id = patientVaccineService.create(new PatientVaccineDto(
                UUID.randomUUID(),
                patientDto,
                vaccineDto,
                command.getStatus(),
                new Date()
        ));
       command.setId(id);
    }
}
