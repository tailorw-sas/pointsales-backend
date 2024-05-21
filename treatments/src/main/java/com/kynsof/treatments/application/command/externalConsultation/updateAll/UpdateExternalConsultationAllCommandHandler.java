package com.kynsof.treatments.application.command.externalConsultation.updateAll;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.*;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.rules.externalconsultation.ExternalConsultationCreateAtNotEqualsRule;
import com.kynsof.treatments.domain.service.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UpdateExternalConsultationAllCommandHandler implements ICommandHandler<UpdateExternalConsultationAllCommand> {

    private final IExternalConsultationService externalConsultationService;
    private final IMedicinesService medicinesService;
    private final ITreatmentService treatmentService;
    private final IDiagnosisService diagnosisService;
    private final IExamService examService;

    public UpdateExternalConsultationAllCommandHandler(IExternalConsultationService externalConsultationService,
            IMedicinesService medicinesService,
            ITreatmentService treatmentService,
            IDiagnosisService diagnosisService,
            IExamService examService) {
        this.externalConsultationService = externalConsultationService;
        this.medicinesService = medicinesService;
        this.treatmentService = treatmentService;
        this.diagnosisService = diagnosisService;
        this.examService = examService;
    }

    @Override
    public void handle(UpdateExternalConsultationAllCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "External Consultation ID cannot be null."));
        ExternalConsultationDto externalConsultationDto = externalConsultationService.findById(command.getId());

        RulesChecker.checkRule(new ExternalConsultationCreateAtNotEqualsRule(externalConsultationDto.getConsultationTime()));

        this.delete(externalConsultationDto);

        List<DiagnosisDto> diagnosisDtoList = command.getDiagnosis().stream().map(diagnosisRequest
                -> new DiagnosisDto(UUID.randomUUID(), diagnosisRequest.getIcdCode(), diagnosisRequest.getDescription())).toList();

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
                externalConsultationDto.getExamOrder().getId(),
                command.getExamOrder().getReason(),
                Status.ACTIVE.toString(),
                0.0,
                new Date(),
                externalConsultationDto.getPatient(),
                examDtoList
        );

        externalConsultationDto.setTreatments(treatmentDtoList);
        externalConsultationDto.setDiagnoses(diagnosisDtoList);
        externalConsultationDto.setExamOrder(examOrderDto);

        UpdateIfNotNull.updateIfNotNull(externalConsultationDto::setConsultationReason, command.getConsultationReason());
        UpdateIfNotNull.updateIfNotNull(externalConsultationDto::setMedicalHistory, command.getMedicalHistory());
        UpdateIfNotNull.updateIfNotNull(externalConsultationDto::setPhysicalExam, command.getPhysicalExam());
        UpdateIfNotNull.updateIfNotNull(externalConsultationDto::setObservations, command.getObservations());
        UpdateIfNotNull.updateIfNotNull(externalConsultationDto::setMedicalSpeciality, command.getMedicalSpeciality());

        UUID id = externalConsultationService.update(externalConsultationDto);
        command.setId(id);
    }

    private void delete(ExternalConsultationDto externalConsultationDto) {
        List<UUID> treatmentsToDelete = externalConsultationDto.getTreatments().stream()
                .map(TreatmentDto::getId)
                .collect(Collectors.toList());

        List<UUID> diagnosesToDelete = externalConsultationDto.getDiagnoses().stream()
                .map(DiagnosisDto::getId)
                .collect(Collectors.toList());

        List<UUID> examsToDelete = externalConsultationDto.getExamOrder().getExams().stream()
                .map(ExamDto::getId)
                .collect(Collectors.toList());

        this.deleteTreatments(treatmentsToDelete);
        this.deleteDiagnosis(diagnosesToDelete);
        this.deleteExams(examsToDelete);
    }

    private void deleteTreatments(List<UUID> treatmentIdsToDelete) {
        this.treatmentService.deleteByIds(treatmentIdsToDelete);
    }

    private void deleteDiagnosis(List<UUID> diagnosisIdsToDelete) {
        this.diagnosisService.deleteByIds(diagnosisIdsToDelete);
    }

    private void deleteExams(List<UUID> examIdsToDelete) {
        this.examService.deleteByIds(examIdsToDelete);
    }
}
