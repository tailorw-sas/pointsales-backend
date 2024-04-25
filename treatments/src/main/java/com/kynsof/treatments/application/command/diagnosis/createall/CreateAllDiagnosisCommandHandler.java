package com.kynsof.treatments.application.command.diagnosis.createall;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateAllDiagnosisCommandHandler implements ICommandHandler<CreateAllDiagnosisCommand> {

    private final IDiagnosisService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public CreateAllDiagnosisCommandHandler(IDiagnosisService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public void handle(CreateAllDiagnosisCommand command) {
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
        for (DiagnosisRequest object : command.getPayload()) {
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
