package com.kynsof.treatments.application.command.treatment.createall;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IMedicinesService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateAllTreatmentCommandHandler implements ICommandHandler<CreateAllTreatmentCommand> {

    private final ITreatmentService serviceImpl;
    private final IExternalConsultationService externalConsultationService;
    private final IMedicinesService medicinesService;

    public CreateAllTreatmentCommandHandler(ITreatmentService serviceImpl, IExternalConsultationService externalConsultationService, IMedicinesService medicinesService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
        this.medicinesService = medicinesService;
    }

    @Override
    public void handle(CreateAllTreatmentCommand command) {
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());

        for (CreateAllTreatmentRequest createTreatmentRequest : command.getPayload()) {
            MedicinesDto medicinesDto = this.medicinesService.findById(UUID.fromString(createTreatmentRequest.getMedication()));
            TreatmentDto create = new TreatmentDto(
                    UUID.randomUUID(),
                    createTreatmentRequest.getDescription(),
                    medicinesDto,
                    createTreatmentRequest.getQuantity(),
                    createTreatmentRequest.getMedicineUnit()
            );
            create.setExternalConsultationDto(externalConsultationDto);
            serviceImpl.create(create);
        }
    }
}
