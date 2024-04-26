package com.kynsof.treatments.application.command.treatment.createall;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentRequest;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateAllTreatmentCommandHandler implements ICommandHandler<CreateAllTreatmentCommand> {

    private final ITreatmentService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public CreateAllTreatmentCommandHandler(ITreatmentService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public void handle(CreateAllTreatmentCommand command) {
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());

        for (CreateTreatmentRequest createTreatmentRequest : command.getPayload()) {
            TreatmentDto create = new TreatmentDto(
                    UUID.randomUUID(),
                    createTreatmentRequest.getDescription(),
                    createTreatmentRequest.getMedication(),
                    createTreatmentRequest.getDose(),
                    createTreatmentRequest.getFrequency(),
                    createTreatmentRequest.getDuration()
            );
            create.setExternalConsultationDto(externalConsultationDto);
            serviceImpl.create(create);
        }
    }
}
