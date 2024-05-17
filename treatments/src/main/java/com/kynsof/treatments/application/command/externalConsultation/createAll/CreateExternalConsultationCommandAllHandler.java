package com.kynsof.treatments.application.command.externalConsultation.createAll;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.*;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateExternalConsultationCommandAllHandler implements ICommandHandler<CreateExternalConsultationAllCommand> {

    private final IExternalConsultationService externalConsultationService;
    private final IPatientsService patientsService;
    private final IDoctorService doctorService;
    private final IMedicinesService medicinesService;
    private final IBusinessService businessService;

    public CreateExternalConsultationCommandAllHandler(IExternalConsultationService externalConsultationService,
                                                       IPatientsService patientsService, 
                                                       IDoctorService doctorService, 
                                                       IMedicinesService medicinesService,
                                                       IBusinessService businessService) {
        this.externalConsultationService = externalConsultationService;
        this.patientsService = patientsService;
        this.doctorService = doctorService;
        this.medicinesService = medicinesService;
        this.businessService = businessService;
    }

    @Override
    public void handle(CreateExternalConsultationAllCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        DoctorDto doctorDto = doctorService.findById(command.getDoctorId());
        BusinessDto businessDto = businessService.findById(command.getBusinessId());

        List<DiagnosisDto> diagnosisDtoList = command.getDiagnosis().stream().map(diagnosisRequest ->
                new DiagnosisDto(UUID.randomUUID(), diagnosisRequest.getIcdCode(), diagnosisRequest.getDescription())).toList();

        List<TreatmentDto> treatmentDtoList = command.getTreatments().stream().map(treatmentRequest -> {
            MedicinesDto medicinesDto = medicinesService.findById(treatmentRequest.getMedication());
            return new TreatmentDto(
                    UUID.randomUUID(),
                    treatmentRequest.getDescription(),
                    medicinesDto,
                    treatmentRequest.getQuantity(),
                    treatmentRequest.getMedicineUnit()
            );
        }).toList();

        List<ExamDto> examDtoList = command.getExamOrder().getExams().stream()
                .map(examRequest -> new ExamDto(
                        UUID.randomUUID(),
                        examRequest.getName(),
                        examRequest.getDescription(),
                        examRequest.getType(),
                        "",
                        new Date(),
                        examRequest.getPrice(),
                        examRequest.getCode()
                ))
                .collect(Collectors.toList());
        ExamOrderDto examOrderDto = new ExamOrderDto(
                UUID.randomUUID(),
                command.getExamOrder().getReason(),
                Status.ACTIVE.toString(),
                0.0,
                new Date(),
                patientDto,
                examDtoList
        );

        UUID id = externalConsultationService.createAll(new ExternalConsultationDto(
                UUID.randomUUID(),
                patientDto,
                doctorDto,
                new Date(),
                command.getConsultationReason(),
                command.getMedicalHistory(),
                command.getPhysicalExam(),
                diagnosisDtoList,
                treatmentDtoList,
                command.getObservations(),
                examOrderDto,
                businessDto,
                command.getMedicalSpeciality(),
                ""
        ));
        command.setId(id);
    }
}
