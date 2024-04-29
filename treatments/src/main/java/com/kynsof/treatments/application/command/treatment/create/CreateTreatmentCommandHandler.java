package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IMedicinesService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateTreatmentCommandHandler implements ICommandHandler<CreateTreatmentCommand> {

    private final ITreatmentService serviceImpl;
    private final IExternalConsultationService externalConsultationService;
    private final IMedicinesService medicinesService;

    public CreateTreatmentCommandHandler(ITreatmentService serviceImpl, IExternalConsultationService externalConsultationService, IMedicinesService medicinesService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
        this.medicinesService = medicinesService;
    }

    @Override
    public void handle(CreateTreatmentCommand command) {

        MedicinesDto medicinesDto = this.medicinesService.findById(UUID.fromString(command.getMedication()));
        TreatmentDto create = new TreatmentDto(
                command.getId(), 
                command.getDescription(), 
                medicinesDto, 
                command.getQuantity(),
                command.getMedicineUnit()
        );

        try {
            ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
            create.setExternalConsultationDto(externalConsultationDto);
        } catch (Exception e) {
        }
        serviceImpl.create(create);
    }
}
