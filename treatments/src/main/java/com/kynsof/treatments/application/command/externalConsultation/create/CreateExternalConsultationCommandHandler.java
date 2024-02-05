package com.kynsof.treatments.application.command.externalConsultation.create;

import com.kynsof.treatments.domain.bus.command.ICommandHandler;
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

        List<DiagnosisDto>diagnosisDtoList= command.getDiagnoses().stream()
                .map(diagnosisRequest -> new DiagnosisDto(
                        UUID.randomUUID(),
                        diagnosisRequest.getIcdCode(),
                        diagnosisRequest.getDescription()))
                .collect(Collectors.toList());

        List<TreatmentDto>treatmentDtoList= command.getTreatments().stream()
                .map(treatmentRequest -> new TreatmentDto(
                        UUID.randomUUID(),
                        treatmentRequest.getDescription(),
                        treatmentRequest.getMedication(),
                        treatmentRequest.getDose(),
                        treatmentRequest.getFrequency(),
                        treatmentRequest.getDuration()))
                .collect(Collectors.toList());
        UUID id = externalConsultationService.create(new ExternalConsultationDto(
                UUID.randomUUID(),
                patientDto,
                doctorDto,
                new Date(),
                command.getConsultationReason(),
                command.getMedicalHistory(),
                command.getPhysicalExam(),
               diagnosisDtoList,
               treatmentDtoList,
               command.getObservations()
        ));
       command.setId(id);
    }
}
