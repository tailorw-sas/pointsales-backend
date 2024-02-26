package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UpdateExternalConsultationCommandHandler implements ICommandHandler<UpdateExternalConsultationCommand> {

    private final IExternalConsultationService serviceImpl;

    public UpdateExternalConsultationCommandHandler(IExternalConsultationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateExternalConsultationCommand command) {
        ExternalConsultationDto externalConsultationDto = serviceImpl.findById(command.getId());

        var diagnosisDtoList= command.getDiagnoses().stream()
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

         externalConsultationDto.setConsultationTime(new Date());
         externalConsultationDto.setConsultationReason(command.getConsultationReason());
         externalConsultationDto.setPhysicalExam(command.getPhysicalExam());
         externalConsultationDto.setMedicalHistory(command.getMedicalHistory());
         externalConsultationDto.setMedicalHistory(command.getObservations());
         externalConsultationDto.setTreatments(treatmentDtoList);
         externalConsultationDto.setDiagnoses(diagnosisDtoList);
         externalConsultationDto.setObservations(command.getObservations());
        serviceImpl.update(new ExternalConsultation(
             externalConsultationDto
        ));

    }
}
