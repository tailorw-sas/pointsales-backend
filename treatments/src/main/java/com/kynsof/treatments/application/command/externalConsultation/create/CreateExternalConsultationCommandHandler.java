package com.kynsof.treatments.application.command.externalConsultation.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.*;
import com.kynsof.treatments.domain.service.IDoctorService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateExternalConsultationCommandHandler implements ICommandHandler<CreateExternalConsultationCommand> {

    private final IExternalConsultationService externalConsultationService;
    private final IPatientsService patientsService;
    private final IDoctorService doctorService;

    public CreateExternalConsultationCommandHandler(IExternalConsultationService externalConsultationService, IPatientsService patientsService, IDoctorService doctorService) {
        this.externalConsultationService = externalConsultationService;
        this.patientsService = patientsService;
        this.doctorService = doctorService;
    }

    @Override
    public void handle(CreateExternalConsultationCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        DoctorDto doctorDto = doctorService.findById(command.getDoctorId());

        UUID id = externalConsultationService.create(new ExternalConsultationDto(
                UUID.randomUUID(),
                patientDto,
                doctorDto,
                new Date(),
                command.getConsultationReason(),
                command.getMedicalHistory(),
                command.getPhysicalExam(),
                command.getObservations()
        ));
        command.setId(id);
    }
}
