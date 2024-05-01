package com.kynsof.treatments.application.command.diagnosis.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.application.command.diagnosis.create.DiagnosisRequest;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UpdateDiagnosisCommandHandler implements ICommandHandler<UpdateDiagnosisCommand> {

    private final IDiagnosisService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public UpdateDiagnosisCommandHandler(IDiagnosisService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public void handle(UpdateDiagnosisCommand command) {
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
        List<DiagnosisDto> treatmentDtoList = externalConsultationDto.getDiagnoses();
        for (DiagnosisDto treatmentDto : treatmentDtoList) {
            serviceImpl.delete(treatmentDto.getId());
        }

        for (DiagnosisRequest object : command.getDiagnosis()) {
            DiagnosisDto create = new DiagnosisDto(
                    UUID.randomUUID(),
                    object.getIcdCode(),
                    object.getDescription(),
                    externalConsultationDto
            );
            serviceImpl.create(create);
        }
    }
}
