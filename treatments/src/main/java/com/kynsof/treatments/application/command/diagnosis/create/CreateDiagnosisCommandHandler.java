package com.kynsof.treatments.application.command.diagnosis.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class CreateDiagnosisCommandHandler implements ICommandHandler<CreateDiagnosisCommand> {

    private final IDiagnosisService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public CreateDiagnosisCommandHandler(IDiagnosisService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public void handle(CreateDiagnosisCommand command) {
        DiagnosisDto create = new DiagnosisDto(
                command.getId(), 
                command.getIcdCode(), 
                command.getDescription()
        );

        try {
            ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
            create.setExternalConsultation(externalConsultationDto);
        } catch (Exception e) {
        }

        serviceImpl.create(create);
    }
}
