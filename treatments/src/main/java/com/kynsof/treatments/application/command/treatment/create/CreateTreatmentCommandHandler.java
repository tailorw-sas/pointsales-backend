package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class CreateTreatmentCommandHandler implements ICommandHandler<CreateTreatmentCommand> {

    private final ITreatmentService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public CreateTreatmentCommandHandler(ITreatmentService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public void handle(CreateTreatmentCommand command) {
        TreatmentDto create = new TreatmentDto(
                command.getId(), 
                command.getDescription(), 
                command.getMedication(), 
                command.getDose(), 
                command.getFrequency(), 
                command.getDuration()
        );

        try {
            ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
            create.setExternalConsultationDto(externalConsultationDto);
        } catch (Exception e) {
        }
        serviceImpl.create(create);
    }
}
