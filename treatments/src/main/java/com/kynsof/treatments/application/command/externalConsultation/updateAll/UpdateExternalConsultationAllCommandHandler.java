package com.kynsof.treatments.application.command.externalConsultation.updateAll;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.*;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IDoctorService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IMedicinesService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UpdateExternalConsultationAllCommandHandler implements ICommandHandler<UpdateExternalConsultationAllCommand> {

    private final IExternalConsultationService externalConsultationService;
    private final IPatientsService patientsService;
    private final IDoctorService doctorService;
    private final IMedicinesService medicinesService;
    public UpdateExternalConsultationAllCommandHandler(IExternalConsultationService externalConsultationService,
                                                       IPatientsService patientsService, IDoctorService doctorService, IMedicinesService medicinesService) {
        this.externalConsultationService = externalConsultationService;
        this.patientsService = patientsService;
        this.doctorService = doctorService;
        this.medicinesService = medicinesService;
    }

    @Override
    public void handle(UpdateExternalConsultationAllCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        ExternalConsultationDto externalConsultationDto = externalConsultationService.findById(command.getId());
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

        externalConsultationDto.setExamOrder(examOrderDto);
        externalConsultationDto.setTreatments(treatmentDtoList);
        externalConsultationDto.setDiagnoses(diagnosisDtoList);

        UUID id = externalConsultationService.update(externalConsultationDto);
        command.setId(id);
    }
}
